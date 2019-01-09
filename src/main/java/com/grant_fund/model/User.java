package com.grant_fund.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected int userId;

    @NotNull
    @Size(min = 3, max = 15)
    @Column(name = "firstname", length = 30, nullable = false)
    protected String firstname;

    @NotNull
    @Size(min = 3, max = 15)
    @Column(name = "lastname", length = 30, nullable = false)
    protected String lastname;

    @NotNull
    @Size(min = 4, max = 15)
    @Column(name = "username", length = 20, unique = true, nullable = false)
    protected String username;

    @NotNull
    @Size(min = 6, max = 15)
    @Column(name = "password", length = 20, nullable = false)
    protected String password;


    public User(){
    }

    public User(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
