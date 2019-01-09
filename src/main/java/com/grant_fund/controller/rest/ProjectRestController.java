package com.grant_fund.controller.rest;

import com.grant_fund.model.Client;
import com.grant_fund.model.Project;
import com.grant_fund.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getProjects() {

        List<Project> projects = projectService.findAll();
        if (projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Project project: projects) {
            Client client = project.getClient();
            client.setUsername(null);
            client.setPassword(null);
            client.setClientDetails(null);
            client.setProjects(null);
            project.getGrantFund().setProjects(null);
        }

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Project> getProject(@PathVariable("id") Integer id) {

        Project project = projectService.find(id);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Client client = project.getClient();
        client.setUsername(null);
        client.setPassword(null);
        client.setClientDetails(null);
        client.setProjects(null);
        project.getGrantFund().setProjects(null);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Project> saveProject(@RequestBody Project project) {

        project.setProjectId(0);
        Project savedProject = projectService.save(project);
        return new ResponseEntity<>(savedProject, HttpStatus.OK);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Project> updateProject(@PathVariable("id") Integer id,
                                                 @RequestBody Project project) {
        if (id != null && projectService.find(id) != null) {
            project.setProjectId(id);
            projectService.save(project);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удаляет проекты не привязанные к клиентам и фондам.
     */
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void clearProjects() {
        projectService.deleteAll();
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Project> deleteProject(@PathVariable("id") Integer id) {

        Project project = projectService.find(id);
        if (project != null) {
            projectService.delete(project);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
