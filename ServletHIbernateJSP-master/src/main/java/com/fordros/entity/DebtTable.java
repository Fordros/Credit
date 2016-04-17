package com.fordros.entity;

/**
 * Created by Fordros on 17.04.2016.
 */
public class DebtTable {
    private Integer id; //номер по порядку
    private String date; // текущий день
    private String debts; // задолженность на день
    private String pay; //платеж
    private String percentPrincipalDebt; // % основной задолженности на дату
    private String percentPastDueDebts; // % просроченной задолженности на дату
    private String sumPercentPrincipalDebt; // сумма % основной задолженности на дату
    private String sumPercentPastDueDebts;  // сумма % просроченной задолженности на дату
    private String fullDebts; //общая задолженность на день

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDebts() {
        return debts;
    }

    public void setDebts(String debts) {
        this.debts = debts;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getPercentPrincipalDebt() {
        return percentPrincipalDebt;
    }

    public void setPercentPrincipalDebt(String percentPrincipalDebt) {
        this.percentPrincipalDebt = percentPrincipalDebt;
    }

    public String getPercentPastDueDebts() {
        return percentPastDueDebts;
    }

    public void setPercentPastDueDebts(String percentPastDueDebts) {
        this.percentPastDueDebts = percentPastDueDebts;
    }

    public String getSumPercentPrincipalDebt() {
        return sumPercentPrincipalDebt;
    }

    public void setSumPercentPrincipalDebt(String sumPercentPrincipalDebt) {
        this.sumPercentPrincipalDebt = sumPercentPrincipalDebt;
    }

    public String getSumPercentPastDueDebts() {
        return sumPercentPastDueDebts;
    }

    public void setSumPercentPastDueDebts(String sumPercentPastDueDebts) {
        this.sumPercentPastDueDebts = sumPercentPastDueDebts;
    }

    public String getFullDebts() {
        return fullDebts;
    }

    public void setFullDebts(String fullDebts) {
        this.fullDebts = fullDebts;
    }
}
