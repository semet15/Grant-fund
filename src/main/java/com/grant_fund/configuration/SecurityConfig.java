package com.grant_fund.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/grantfund/list").access("permitAll()")
            .antMatchers("/client/signup-form", "/client/signup").access("isAnonymous()")
            .antMatchers("/user/**").access("isAuthenticated()")
            .antMatchers("/client/**", "/project/**", "/client-details/**").access("hasRole('ROLE_CLIENT')")
            .antMatchers("/admin/**", "/grantfund/**").access("hasRole('ROLE_ADMIN')")
            .and()
            .formLogin().loginPage("/login").permitAll()
            .usernameParameter("j_username").passwordParameter("j_password")
            .failureUrl("/login?error=true")
            .defaultSuccessUrl("/user/redirect-by-role")
            .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/start-page")
            .and()
            .exceptionHandling().accessDeniedPage("/access-denied");
    }
}