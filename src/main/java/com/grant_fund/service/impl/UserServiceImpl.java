package com.grant_fund.service.impl;

import com.grant_fund.model.User;
import com.grant_fund.repository.UserRepository;
import com.grant_fund.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User find(Integer id) {
        return userRepository.findByUserId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User find(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }
}
