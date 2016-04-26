package com.fordros.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Fordros on 13.04.2016.
 */
public class CreditDebt {
    private Integer debts; //задолженность на которую считается %
    private Integer percentPrincipalDebt; // % основной задолженности на дату
    private Integer percentPastDueDebts; // % просроченной задолженности на дату



    public Integer calcDebts(Integer debtsAmount,  Integer payment) {
        debts = debtsAmount  + payment;
        return debts;
    }

    public Integer calcPercentPrincipalDebt(Integer debts, Integer creditLimit, Integer percentDebtDue) {
        if (debts > 0) {
            percentPrincipalDebt = (debts - creditLimit) * percentDebtDue / 100 / 360;
        } else {
            percentPrincipalDebt = creditLimit * percentDebtDue / 100 / 360;
        }
        return percentPrincipalDebt;
    }

    public Integer calcPercentPastDueDebts(Integer debts, Integer percentPastDue) {
        if (debts < 0) {
            percentPastDueDebts = debts * percentPastDue / 360;
        } else {
            percentPastDueDebts = 0;
        }
        return percentPastDueDebts;
    }

    public boolean isBillingDate(Date postDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(postDate);
        int dateBilling = 25;
        int dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        boolean result = dateBilling == dateOfMonth;

        return result;
    }

    public boolean isTerminateDate(Date postDate, Date limitTerminationDate) {
        Calendar dateForCheck = Calendar.getInstance();
        Calendar terminationDate = Calendar.getInstance();
        dateForCheck.setTime(postDate);
        terminationDate.setTime(limitTerminationDate);
        boolean result = false;
        result = (dateForCheck.get(Calendar.MONTH) == terminationDate.get(Calendar.MONTH) &&
                dateForCheck.get(Calendar.YEAR) == terminationDate.get(Calendar.YEAR) &&
                dateForCheck.get(Calendar.DAY_OF_MONTH) == terminationDate.get(Calendar.DAY_OF_MONTH));
        return result;
    }

    public boolean isNowPaymentDate(Date postDate, List<Payment> payments) {
        Calendar dateForCheck = Calendar.getInstance();
        dateForCheck.setTime(postDate);
        Calendar datePayment = Calendar.getInstance();
        boolean result = false;
        for (int i = 0; i < payments.size(); i++) {
            datePayment.setTime(payments.get(i).getDatePayment());
            int dayCheck = dateForCheck.get(Calendar.DAY_OF_MONTH);
            int monthCheck = dateForCheck.get(Calendar.MONTH);
            int yearCheck = dateForCheck.get(Calendar.YEAR);

            int dayPayment = datePayment.get(Calendar.DAY_OF_MONTH);
            int monthPayment = datePayment.get(Calendar.MONTH);
            int yearPayment = datePayment.get(Calendar.YEAR);

            if(result = (dateForCheck.get(Calendar.MONTH) == datePayment.get(Calendar.MONTH) &&
                    dateForCheck.get(Calendar.YEAR) == datePayment.get(Calendar.YEAR) &&
                    dateForCheck.get(Calendar.DAY_OF_MONTH) == datePayment.get(Calendar.DAY_OF_MONTH))){
                return result;
            }
        }
        return result;
    }

    public boolean isDecreaseDate(Date postDate, Date limitDecreaseDate) {
        boolean result = false;
        if(limitDecreaseDate != null){
            Calendar dateForCheck = Calendar.getInstance();
            Calendar dateDecrease = Calendar.getInstance();
            dateForCheck.setTime(postDate);
            dateDecrease.setTime(limitDecreaseDate);
            result = (dateForCheck.get(Calendar.DAY_OF_MONTH) == dateDecrease.get(Calendar.DAY_OF_MONTH));
        }
        return result;
    }

    public Integer getAmount(Date postDate, List<Payment> payments) {
        Calendar dateForCheck = Calendar.getInstance();
        dateForCheck.setTime(postDate);
        Calendar datePayment = Calendar.getInstance();
        Integer result = 0;
        for (int i = 0; i < payments.size(); i++) {
            datePayment.setTime(payments.get(i).getDatePayment());
            if( (dateForCheck.get(Calendar.MONTH) == datePayment.get(Calendar.MONTH) &&
                    dateForCheck.get(Calendar.YEAR) == datePayment.get(Calendar.YEAR) &&
                    dateForCheck.get(Calendar.DAY_OF_MONTH) == datePayment.get(Calendar.DAY_OF_MONTH))){
               result += payments.get(i).getAmount();
            }
        }
        return result;
    }

    public String getFormattedAmount(Integer amount){
        String result = amount.toString();
        String grn;
        String kop;
        String prefix = "";
        if(result.startsWith("-")){
            result = result.substring(1,result.length());
            prefix = "-";
        }
        if(result.length()>2){
            grn = result.substring(0,result.length() - 2);
            kop = result.substring(result.length() - 2,result.length());
        }else {
            grn = "0";
            kop = result;
        }

        if(kop.length() == 1){
            kop = result + "0";
        }

        result = prefix + grn + "." + kop;
        return result;
    }


}
