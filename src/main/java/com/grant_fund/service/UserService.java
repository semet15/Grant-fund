package com.grant_fund.service;

import com.grant_fund.model.User;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {

     void save(User user);

     User find(Integer id);

     User find(String username);

     List<User> findAll();

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     void delete(User user);
}
