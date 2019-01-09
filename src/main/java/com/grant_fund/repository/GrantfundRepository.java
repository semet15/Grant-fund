package com.grant_fund.repository;

import com.grant_fund.model.GrantFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrantfundRepository extends JpaRepository<GrantFund, Integer> {
    GrantFund findByGrantFundId(Integer id);
}
