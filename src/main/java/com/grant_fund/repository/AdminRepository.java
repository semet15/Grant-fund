package com.grant_fund.repository;

import com.grant_fund.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUserId(Integer id);

    Admin findByUsername(String username);
}
