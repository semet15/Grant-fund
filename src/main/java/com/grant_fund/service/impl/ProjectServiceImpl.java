package com.grant_fund.service.impl;

import com.grant_fund.model.Project;
import com.grant_fund.repository.ProjectRepository;
import com.grant_fund.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    @Transactional(readOnly = true)
    public Project find(Integer id) {
        return projectRepository.findByProjectId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Project project) {
        projectRepository.delete(project);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        projectRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        projectRepository.deleteAll();
    }
}
