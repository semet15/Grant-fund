package com.grant_fund.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Table(name = "clientdetails")
public class ClientDetails implements Serializable {

    @Id
    private int clientId;

    @Column(name = "address", length = 50, nullable = true)
    private String address;

    @DecimalMin("0")
    @Column(name = "phone", length = 12, nullable = true)
    private Long phone;

    @Pattern(regexp = "^$|^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
    @Column(name = "email", length = 30, nullable = true)
    private String email;

    @OneToOne
    @MapsId
    private Client client;

    public ClientDetails() {
    }

    public ClientDetails(String address, Long phone, String email, Client client) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.client = client;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
