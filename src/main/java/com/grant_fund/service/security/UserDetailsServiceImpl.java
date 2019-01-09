package com.grant_fund.service.security;

import com.grant_fund.model.Admin;
import com.grant_fund.model.Client;
import com.grant_fund.repository.AdminRepository;
import com.grant_fund.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), grantedAuthorities);
        }

        Client client = clientRepository.findByUsername(username);
        if (client != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
            return new org.springframework.security.core.userdetails.User(client.getUsername(), client.getPassword(), grantedAuthorities);
        }

        throw new UsernameNotFoundException("User not found");
    }
}
