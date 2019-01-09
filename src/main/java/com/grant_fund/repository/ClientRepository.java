package com.grant_fund.repository;

import com.grant_fund.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByUserId(Integer id);

    Client findByUsername(String username);
}
