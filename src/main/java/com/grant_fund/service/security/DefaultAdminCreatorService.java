package com.grant_fund.service.security;

import com.grant_fund.model.Admin;
import com.grant_fund.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolationException;

@Service
public class DefaultAdminCreatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAdminCreatorService.class);

    @Autowired
    private Admin defaultAdmin;

    @Autowired
    private AdminRepository adminRepository;

    @PostConstruct
    private void saveDefaultAdmin(){
        try {
            if (adminRepository.findByUsername(defaultAdmin.getUsername()) != null) {
                LOGGER.warn(" Username '{}' already exists.", defaultAdmin.getUsername());
            } else {
                adminRepository.saveAndFlush(defaultAdmin);
                LOGGER.info(" Admin with username '{}' is registred.", defaultAdmin.getUsername());
            }

        } catch (ConstraintViolationException e) {
            LOGGER.warn("Wrong data format.", e);
        }
    }
}
