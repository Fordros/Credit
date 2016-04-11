package com.fordros.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by IvashcRY on 15.03.2016.
 */
@Entity
@Table(name = "account")
public class Client {

    private BigDecimal id;
    private BigDecimal SSN;
    private BigDecimal accountNumber;
    private BigDecimal balance;
    private BigDecimal creditLimit;
    private Date limitTerminationDate; //дата окончания кредитного лимита
    private Date limitDecreaseDate; //дата уменьшения кредитного лимита



    private Integer percentDebtDue; //процентная ставка на срочную задолженность (тело КЛ)
    private Integer percentPastDue; //процентная ставка на просроченную задолженность (несанкционированный овер)
    private Addr addr;


    public Client(){

    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACC_ID", unique = true, nullable = false)
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Column(name = "ACCOUNT_NUMBER", unique = true, nullable = false, length = 17)
    public BigDecimal getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigDecimal accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "BALANCE", unique = false, nullable = false, length = 50)
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Column(name = "CREDIT_LIMIT", unique = false, nullable = false, length = 13)
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Column(name = "SSN", unique = true, nullable = false, length = 10)
    public BigDecimal getSSN() {
        return SSN;
    }

    public void setSSN(BigDecimal SSN) {
        this.SSN = SSN;
    }

    @Column(name = "DATE_END", unique = false, nullable = false)
    public Date getLimitTerminationDate() {
        return limitTerminationDate;
    }

    public void setLimitTerminationDate(Date limitTerminationDate) {
        this.limitTerminationDate = limitTerminationDate;
    }

    @Column(name = "DATE_DESCREASE", unique = false, nullable = false)
    public Date getLimitDecreaseDate() {
        return limitDecreaseDate;
    }

    public void setLimitDecreaseDate(Date limitDecreaseDate) {
        this.limitDecreaseDate = limitDecreaseDate;
    }

    @Column(name = "PERSENT_DEBIT_DUE", unique = false, nullable = false)
    public Integer getPercentDebtDue() {
        return percentDebtDue;
    }

    public void setPercentDebtDue(Integer percentDebtDue) {
        this.percentDebtDue = percentDebtDue;
    }

    @Column(name = "PERCENT_PAST_DUE", unique = false, nullable = false)
    public Integer getPercentPastDue() {
        return percentPastDue;
    }

    public void setPercentPastDue(Integer percentPastDue) {
        this.percentPastDue = percentPastDue;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    public Addr getAddr() {
        return addr;
    }

    public void setAddr(Addr addr) {
        this.addr = addr;
    }

    @Override
    public String toString(){
        return "User{" +
                "firstName='" + accountNumber + '\'' +
                ", lastName='" + balance + '\'' +
                ", phone='" + creditLimit +
                '}';
    }
}
