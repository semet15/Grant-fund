package com.grant_fund.repository;

import com.grant_fund.model.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Integer> {
    ClientDetails findByClientId(Integer id);
}
