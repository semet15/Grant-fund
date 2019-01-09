package com.grant_fund.service;

import com.grant_fund.model.Client;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ClientService {

     void save(Client client);

     Client find(Integer id);

     Client find(String username);

     List<Client> findAll();

     @PreAuthorize("hasRole('ROLE_CLIENT')")
     void delete(Client client);
}
