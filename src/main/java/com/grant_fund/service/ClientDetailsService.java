package com.grant_fund.service;

import com.grant_fund.model.ClientDetails;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ClientDetailsService {

     void save(ClientDetails clientDetails);

     ClientDetails find(Integer id);

     List<ClientDetails> findAll();

     @PreAuthorize("hasRole('ROLE_CLIENT')")
     void delete(ClientDetails clientDetails);
}
