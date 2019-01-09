package com.grant_fund.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {

    public Admin() {
    }

    public Admin(String firstname, String lastname, String username, String password) {
        super(firstname, lastname, username, password);
    }
}
