package com.grant_fund.service;

import com.grant_fund.model.GrantFund;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface GrantFundService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void save(GrantFund grantFund);

    GrantFund find(Integer id);

    List<GrantFund> findAll();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(GrantFund grantFund);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    boolean generateResults(GrantFund grantFund);
}
