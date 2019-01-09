package com.grant_fund.service.impl;

import com.grant_fund.model.Client;
import com.grant_fund.repository.ClientRepository;
import com.grant_fund.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public void save(Client client) {
        clientRepository.saveAndFlush(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Client find(Integer id) {
        return clientRepository.findByUserId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Client find(String username) {
        return clientRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Client client) {
        clientRepository.delete(client);
    }
}
