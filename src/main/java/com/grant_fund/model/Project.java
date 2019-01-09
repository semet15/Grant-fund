package com.grant_fund.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "project")
public class Project implements  Cloneable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @DecimalMin("0")
    @Column(name = "sum", length = 11, nullable = false)
    private long sum;

    @NotNull
    @DecimalMin("0")
    @DecimalMax("100")
    @Column(name = "expertMark", length = 3, nullable = false)
    private int expertMark;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "f_clientId")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "f_grantFundId")
    private GrantFund grantFund;

    public Project(){
    }

    public Project(String name, long sum, int expertMark, Date date, Client client, GrantFund grantFund) {
        this.name = name;
        this.sum = sum;
        this.expertMark = expertMark;
        this.date = date;
        this.client = client;
        this.grantFund = grantFund;
    }

    public Project clone() throws CloneNotSupportedException {
        return (Project) super.clone();
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public int getExpertMark() {
        return expertMark;
    }

    public void setExpertMark(int expertMark) {
        this.expertMark = expertMark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public GrantFund getGrantFund() {
        return grantFund;
    }

    public void setGrantFund(GrantFund grantFund) {
        this.grantFund = grantFund;
    }
}
