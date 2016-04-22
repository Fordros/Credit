package com.fordros.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by IvashcRY on 15.03.2016.
 */
@Entity
@Table(name = "account")
public class Account implements Serializable{

    private Integer id;
    private Integer acc_id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Integer ssn;
    private Date dateDog;
    private String numberDog;
    private String accountNumber;
    private Integer balance;
    private Integer creditLimit;
    private Integer decreaseAmount; //сумма уменьшения кредитного лимита
    private Date limitTerminationDate; //дата окончания кредитного лимита
    private Date limitDecreaseDate; //дата уменьшения кредитного лимита
    private Integer percentDebtDue; //процентная ставка на срочную задолженность (тело КЛ)
    private Integer percentPastDue; //процентная ставка на просроченную задолженность (несанкционированный овер)
    private Set<Payment> payments = new HashSet<>();


    public Account(){

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACC_ID", nullable = false, insertable = true, updatable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ACC_ID", nullable = false, insertable = false, updatable = false)
    public Integer getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(Integer acc_id) {
        this.acc_id = acc_id;
    }

    @Column(name = "ACCOUNT_NUMBER", unique = true, nullable = false, length = 17)
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "BALANCE", unique = false, nullable = false, length = 50)
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Column(name = "CREDIT_LIMIT", unique = false, nullable = false, length = 13)
    public Integer getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Column(name = "SSN", unique = true, nullable = false, length = 10)
    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer SSN) {
        this.ssn = SSN;
    }

    @Column(name = "DATE_END", unique = false, nullable = false)
    public Date getLimitTerminationDate() {
        return limitTerminationDate;
    }

    public void setLimitTerminationDate(Date limitTerminationDate) {
        this.limitTerminationDate = limitTerminationDate;
    }

    @Column(name = "DATE_DECREASE", unique = false, nullable = false)
    public Date getLimitDecreaseDate() {
        return limitDecreaseDate;
    }

    public void setLimitDecreaseDate(Date limitDecreaseDate) {
        this.limitDecreaseDate = limitDecreaseDate;
    }

    @Column(name = "PERCENT_DEBIT_DUE", unique = false, nullable = false)
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

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "MIDDLE_NAME")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "DATE_DOG")
    public Date getDateDog() {
        return dateDog;
    }

    public void setDateDog(Date dateDog) {
        this.dateDog = dateDog;
    }

    @Column(name = "NUMBER_DOG")
    public String getNumberDog() {
        return numberDog;
    }

    public void setNumberDog(String numberDog) {
        this.numberDog = numberDog;
    }

    @Column(name = "DECREASE_AMOUNT")
    public Integer getDecreaseAmount() {
        return decreaseAmount;
    }

    public void setDecreaseAmount(Integer limitDecrease) {
        this.decreaseAmount = limitDecrease;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    public Set<Payment> getPayments() {
        return this.payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public void addPayments(Payment payment) {
        payment.setAccount(this);
        this.payments.add(payment);
    }

    @Override
    public String toString(){
        return "User{" +
                "acc='" + accountNumber + '\'' +
                ", balance='" + balance + '\'' +
                ", credit limit='" + creditLimit + '\'' +
                ", date end='" + limitTerminationDate + '\'' +
                ", date decrease='" + limitDecreaseDate + '\'' +
                ", % limit='" + percentDebtDue + '\'' +
                ", % overdraft='" + percentPastDue + '\'' +
                '}';
    }
}
