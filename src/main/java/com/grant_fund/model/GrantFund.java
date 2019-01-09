package com.grant_fund.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "grantfund")
public class GrantFund implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int grantFundId;

    @DecimalMin("0")
    @Column(name = "grantFundSum", length = 11, nullable = true)
    private long grantFundSum;

    @DecimalMin("0")
    @Column(name = "minSum", length = 11, nullable = true)
    private long minSum;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition="enum('ACTIVE','FINISHED')", nullable = false)
    private State state;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm" )
    @Column(name = "endingDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endingDate;

    @Valid
    @OneToMany(mappedBy = "grantFund", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Project> projects;

    public GrantFund(){
    }

    public GrantFund(long grantFundSum, long minSum, State state, Date endingDate, List<Project> projects) {
        this.grantFundSum = grantFundSum;
        this.minSum = minSum;
        this.state = state;
        this.endingDate = endingDate;
        this.projects = projects;
    }

    public int getGrantFundId() {
        return grantFundId;
    }

    public void setGrantFundId(int grantFundId) {
        this.grantFundId = grantFundId;
    }

    public long getGrantFundSum() {
        return grantFundSum;
    }

    public void setGrantFundSum(long grantFundSum) {
        this.grantFundSum = grantFundSum;
    }

    public long getMinSum() {
        return minSum;
    }

    public void setMinSum(long minSum) {
        this.minSum = minSum;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    //ACTIVE, если фонд не распределён и FINISHED,если фонд распределён
    public enum State {
        ACTIVE, FINISHED;

        public String toString() {
            return super.toString();
        }
    }
}
