package com.fordros.entity;

import javax.persistence.*;
import java.util.Date;




/**
 * Created by IvashcRY on 15.03.2016.
 */
@Entity
@Table(name = "payments")
public class Payments {
    private Integer id;
    private String accountNumber;
    private Integer amount;
    private Date datePayment;
    private Account account;

    public Payments(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ACCOUNT_NUMBER")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "AMOUNT")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_PAYMENT")
    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACC_ID", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public String toString(){
        return "Payments{" +
                "acc='" + accountNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + datePayment + '\'' +
                '}';
    }


}

