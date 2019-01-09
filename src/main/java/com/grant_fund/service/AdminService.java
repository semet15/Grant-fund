package com.grant_fund.service;

import com.grant_fund.model.Admin;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface AdminService {

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     void save(Admin admin);

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     Admin find(Integer id);

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     Admin find(String username);

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     List<Admin> findAll();

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     void delete(Admin admin);
}
