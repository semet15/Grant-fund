package com.grant_fund.controller;

import com.grant_fund.model.Client;
import com.grant_fund.model.GrantFund;
import com.grant_fund.model.Project;
import com.grant_fund.service.ClientService;
import com.grant_fund.service.GrantFundService;
import com.grant_fund.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private GrantFundService grantFundService;

    @Autowired
    private ClientService clientService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ModelAndView getProjectsList(Authentication authentication, ModelAndView modelAndView) {
        Client client = clientService.find(authentication.getName());
        modelAndView.addObject("client", client);

        List<Project> projects = projectService.findAll();
        if (!projects.isEmpty()) {
            projects = projects.stream()
                    .filter(p -> p.getClient().getUserId() == client.getUserId())
                    .sorted((g1, g2) -> g2.getDate().compareTo(g1.getDate()))
                    .collect(Collectors.toList());
        }

        modelAndView.addObject("projects", projects);
        modelAndView.setViewName("projects-list");
        return modelAndView;
    }

    @RequestMapping(value="/registring-form", method = RequestMethod.GET)
    public ModelAndView getProjectRegistringForm(Authentication authentication, ModelAndView modelAndView) {
        Client client = clientService.find(authentication.getName());
        modelAndView.addObject("client", client);

        if (grantFundService.findAll().stream().noneMatch(g -> g.getState() == GrantFund.State.ACTIVE)) {
            modelAndView.setViewName("redirect:/project/list?error=true");
            return modelAndView;
        }

        modelAndView.addObject("project", new Project());
        modelAndView.setViewName("project-registring-form");
        return modelAndView;
    }

    @RequestMapping(value="/registring", method = RequestMethod.POST)
    public ModelAndView registerProject(@Valid @ModelAttribute("project") Project project,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView,
                                        Authentication authentication) {
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("project-registring-form");
            return modelAndView;
        }

        Client client = clientService.find(authentication.getName());
        client.setProjects(new ArrayList<Project>(Arrays.asList(project)));
        project.setClient(client);
        project.setDate(new Date());

        GrantFund grantFund = grantFundService.findAll().stream().filter(g -> g.getState() == GrantFund.State.ACTIVE).findFirst().get();
        if (grantFund == null) {
            modelAndView.setViewName("redirect:/project/list?error=true");
            return modelAndView;
        } else {
            project.setGrantFund(grantFund);
        }

        clientService.save(client);
        modelAndView.setViewName("redirect:/project/list");
        return modelAndView;
    }
}
