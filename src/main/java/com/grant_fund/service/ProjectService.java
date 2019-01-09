package com.grant_fund.service;

import com.grant_fund.model.Project;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ProjectService {

     @PreAuthorize("hasRole('ROLE_CLIENT')")
     Project save(Project project);

     @PreAuthorize("isAuthenticated()")
     Project find(Integer id);

     @PreAuthorize("isAuthenticated()")
     List<Project> findAll();

     @PreAuthorize("hasRole('ROLE_CLIENT')")
     void delete(Project project);

    @PreAuthorize("hasRole('ROLE_CLIENT')")
     void deleteAll();

    @PreAuthorize("hasRole('ROLE_CLIENT')")
     void delete(Integer id);
}
