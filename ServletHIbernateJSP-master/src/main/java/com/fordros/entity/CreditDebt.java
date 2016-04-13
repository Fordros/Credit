package com.fordros.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Fordros on 13.04.2016.
 */
public class CreditDebt {
    private Integer debts; //задолженность на которую считается %
    private Integer	percentPrincipalDebt; // % основной задолженности на дату
    private Integer	percentPastDueDebts; // % просроченной задолженности на дату
    private Integer summPercentPrincipalDebt;
    private Integer summPercentPastDueDebts;


    public Integer calcDebts(Integer amount, Integer creditLimit, Integer payment){
        debts = amount + creditLimit + payment;
        return debts;
    }

    public Integer calcPercentPrincipalDebt (Integer debts, Integer creditLimit, Integer percentDebtDue){
        if(debts>0){
            percentPrincipalDebt = (debts - creditLimit) * percentDebtDue / 100 / 360;
        }else{
            percentPrincipalDebt = creditLimit * percentDebtDue / 100 / 360;
        }
        return percentPrincipalDebt;
    }

    public Integer calcPercentPastDueDebts (Integer debts, Integer percentPastDue){
        if(debts<0){
            percentPastDueDebts = debts * percentPastDue / 360;
        }else{
            percentPastDueDebts = 0;
        }
        return percentPastDueDebts;
    }

    public boolean isBillingDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dateBilling = 26;
        int dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        boolean result = dateBilling == dateOfMonth;

        return result;
    }

}
