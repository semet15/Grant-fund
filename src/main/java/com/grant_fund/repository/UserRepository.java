package com.grant_fund.repository;

import com.grant_fund.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(Integer id);

    User findByUsername(String username);
}
