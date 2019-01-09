package com.grant_fund.service.impl;

import com.grant_fund.model.GrantFund;
import com.grant_fund.model.Project;
import com.grant_fund.repository.GrantfundRepository;
import com.grant_fund.service.GrantFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class GrantFundServiceImpl implements GrantFundService {

    @Autowired
    private GrantfundRepository grantfundRepository;

    @Override
    @Transactional
    public void save(GrantFund grantFund) {
        grantfundRepository.save(grantFund);
    }

    @Override
    @Transactional(readOnly = true)
    public GrantFund find(Integer id) {
        return grantfundRepository.findByGrantFundId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GrantFund> findAll() {
        return grantfundRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(GrantFund grantFund) {
        grantfundRepository.delete(grantFund);
    }

    @Override
    public boolean generateResults(GrantFund grantFund) {
        List<Project> projects = grantFund.getProjects();
        long grantFundSum = grantFund.getGrantFundSum();
        long minSum = grantFund.getMinSum();

        //Выходим, если в фонде нет зарегистрированных проектов
        if (projects.isEmpty()) {
            grantFund.setState(GrantFund.State.FINISHED);
            return false;
        }

        //Для дальнейших расчётов создаём копию списка проектов
        List<Project> calcProjects = cloneProjects(projects);

        boolean hasInsufficientSum = true;          //расчитана сумма гранта для проекта, которая меньше минимально допустимой для проекта (запрашиваемой)
        boolean hasSmallSum = true;                 //расчитана сумма гранта для проекта, которая меньше минимальной суммы гранта

        while (hasInsufficientSum || hasSmallSum) {
            hasInsufficientSum = false;
            hasSmallSum = false;

            int sumOfMark = 0;
            for (Project calcProject : calcProjects) {
                if (calcProject != null) {
                    sumOfMark += calcProject.getExpertMark();
                }
            }

            //вычисляем сумму гранта для каждого проекта копии списка проектов
            for (Project calcProject : calcProjects) {
                if (calcProject != null) {
                    int expertMark = calcProject.getExpertMark();
                    long calcSum = Math.round(1.0 * expertMark / sumOfMark * grantFundSum);
                    calcProject.setSum(calcSum);

                    long requestedSum = projects.get(calcProjects.indexOf(calcProject)).getSum();

                    //Исключаем проекты со слишком большой запрашиваемой суммой, чтобы весь фонд не уходил
                    // проектам с самой высокой оценкой (тоесть максимальная оценка не гарантирует получения гранта)
                    if (requestedSum > 0.7 * grantFundSum && calcSum < 0.8 * requestedSum) {
                        calcProjects.set(calcProjects.indexOf(calcProject), null);
                    }

                    if (calcSum < requestedSum) {
                        hasInsufficientSum = true;
                    }

                    if (calcSum < minSum) {
                        hasSmallSum = true;
                    }
                }
            }

            if (hasInsufficientSum || hasSmallSum) {
                deleteWorstProject(calcProjects);
            }
        }

        //Присваиваем получившим грант проектам расчитанные суммы гранта
        for (Project calcProject : calcProjects) {
            if (calcProject != null) {
                projects.get(calcProjects.indexOf(calcProject)).setSum(calcProject.getSum());
            }
        }

        //Присваиваем не получившим грант проектам значение суммы гранта равное 0
        for (Project project : projects) {
            if (calcProjects.get(projects.indexOf(project)) == null) {
                project.setSum(0);
            }
        }

        grantFund.setState(GrantFund.State.FINISHED);
        return true;
    }

    private List<Project> cloneProjects(List<Project> projects) {
        List<Project> cloneProjects = new ArrayList<>();
        for (Project project : projects) {
            try {
                cloneProjects.add(project.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return cloneProjects;
    }

    //Сортируем копию списка проектов и исключаем худший проект, присваивая его ячейке в списке значение null
    private void deleteWorstProject(List<Project> calcProjects) {

        //Компаратор по экспертной оценке с null-ячейками вверху списка
        class MarkComparator implements Comparator<Project> {
            public int compare(Project pr1, Project pr2) {
                if (pr1 == null) {
                    return 1;
                }
                if (pr2 == null) {
                    return -1;
                }

                if (pr1.getExpertMark() > pr2.getExpertMark()) {
                    return 1;
                } else if (pr1.getExpertMark() < pr2.getExpertMark()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

        //Компаратор по заявленой сумме с большими суммами внизу списка
        class SumComparator implements Comparator<Project> {
            public int compare(Project pr1, Project pr2) {
                if (pr1.getSum() < pr2.getSum()) {
                    return 1;
                } else if (pr1.getSum() > pr2.getSum()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

        //Компаратор по дате регистрации заявки с позними заявками внизу списка
        class DateComparator implements Comparator<Project> {
            public int compare(Project pr1, Project pr2) {
                return pr2.getDate().compareTo(pr1.getDate());
            }
        }

        MarkComparator markComparator = new MarkComparator();
        Comparator<Project> fullCompapator = markComparator.thenComparing(new SumComparator())
                .thenComparing(new DateComparator());
        Project worstProject = Collections.min(calcProjects, fullCompapator);

        calcProjects.set(calcProjects.indexOf(worstProject), null);
    }
}
