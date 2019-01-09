package com.grant_fund.configuration;

import com.grant_fund.model.Admin;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableJpaRepositories({"com.grant_fund.repository"})
public class RootConfig {

    @Autowired
    private Environment env;

    @Bean(destroyMethod = "close")
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("datasource.url"));
        dataSource.setUsername(env.getProperty("datasource.username"));
        dataSource.setPassword(env.getProperty("datasource.password"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabasePlatform(env.getProperty("jpa.dialect"));
        hibernateJpaVendorAdapter.setShowSql(Boolean.parseBoolean(env.getProperty("jpa.show-sql")));
        hibernateJpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getProperty("jpa.generateDdl")));

        emf.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        emf.setPackagesToScan("com.grant_fund.model");
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public Admin defaultAdmin() {
        Admin admin = new Admin();
        admin.setFirstname(env.getProperty("defaultAdmin.firstname"));
        admin.setLastname(env.getProperty("defaultAdmin.lastname"));
        admin.setUsername(env.getProperty("defaultAdmin.username"));
        admin.setPassword(env.getProperty("defaultAdmin.password"));
        return admin;
    }
}
