package com.grant_fund.service.impl;

import com.grant_fund.model.ClientDetails;
import com.grant_fund.repository.ClientDetailsRepository;
import com.grant_fund.service.ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Override
    @Transactional
    public void save(ClientDetails clientDetails) {
        clientDetailsRepository.saveAndFlush(clientDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDetails find(Integer id) {
        return clientDetailsRepository.findByClientId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDetails> findAll() {
        return clientDetailsRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(ClientDetails clientDetails) {
        clientDetailsRepository.delete(clientDetails);
    }
}
