package com.grant_fund.service.impl;

import com.grant_fund.model.Admin;
import com.grant_fund.repository.AdminRepository;
import com.grant_fund.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    @Transactional
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    @Transactional(readOnly = true)
    public Admin find(Integer id) {
        return adminRepository.findByUserId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Admin find(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Admin admin) {
        adminRepository.delete(admin);
    }
}
