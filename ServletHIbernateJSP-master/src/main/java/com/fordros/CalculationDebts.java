package com.fordros;

import com.fordros.entity.Account;
import com.fordros.entity.CreditDebt;
import com.fordros.entity.DebtTable;
import com.fordros.entity.Payment;
import com.fordros.session.AccountManager;
import com.fordros.session.AccountManagerImpl;
import com.fordros.session.PaymentManager;
import com.fordros.session.PaymentManagerImpl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Fordros on 17.04.2016.
 */
public class CalculationDebts {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    AccountManager accountManager = new AccountManagerImpl();
    PaymentManager paymentManager = new PaymentManagerImpl();
    CreditDebt creditDebt = new CreditDebt();

    public List<DebtTable> getCalcDebts(String acc) {
        List<DebtTable> result = new ArrayList<>();

        int debts; //выбранно КЛ
        int accessibleAmount; // доступно КЛ
        int pay; //платеж клиента
        int percentPrincipalDebt; // % основной задолженности на дату
        int percentPastDueDebts; // % просроченной задолженности на дату
        int sumPercentPrincipalDebt = 0;
        int sumPercentPastDueDebts = 0;
        int fullDebts;
        int creditLimit;
        int decreaseAmount;

        Account account = accountManager.findByAccNumber(acc);
        List<Payment> payments = paymentManager.findAllPaymentByAcc(account.getId());
        Calendar amountDate = Calendar.getInstance();

//-----------УСТАНОВКА СТАРТОВЫХ ЗНАЧЕНИЙ---------------

        // дата с которой начинается расчет
        amountDate.set(2015, Calendar.DECEMBER, 25);

        //Сумма КЛ
        creditLimit = account.getCreditLimit();

        //проверка на активность КЛ
        if(creditDebt.isTerminateDate(amountDate.getTime(),account.getLimitTerminationDate())){
           creditLimit = 0;
        }else {
            if(creditLimit == 0){
                creditLimit = 0;
            }else {
                if(creditDebt.isDecreaseDate(amountDate.getTime(),account.getLimitDecreaseDate())){
                    creditLimit -= account.getDecreaseAmount();
                }
            }
        }

        //сумма уменьшения КЛ
        if (account.getDecreaseAmount() == null){
            decreaseAmount = 0;
        }else{
            decreaseAmount = account.getDecreaseAmount();
        }

        //если дата расчета равна дате платежа, тогда выбираем платеж
        if (creditDebt.isNowPaymentDate(amountDate.getTime(), payments)) {
            pay = creditDebt.getAmount(amountDate.getTime(), payments);
        } else {
            pay = 0;
        }

        //подсчет выбранного КЛ
        debts = account.getBalance() + pay;

        //подсчет доступного КЛ
        accessibleAmount = creditLimit + debts;

        //% основной задолженности
        if(accessibleAmount>0){
            percentPrincipalDebt = debts * account.getPercentDebtDue() / 100 / 360;
        }else{
            percentPrincipalDebt = 0;
        }

        //% несанкционированного овера
        if(accessibleAmount<0){
            percentPastDueDebts = debts * account.getPercentPastDue() / 100 / 360;
        }else{
            percentPastDueDebts = 0;
        }

        //Общая задолженность
        fullDebts = debts + pay + sumPercentPrincipalDebt + sumPercentPastDueDebts;



        int[][] percents = new int[3][200];

//---------------------------------------------------ОБЩИЙ ЦИКЛ

        for (int i = 0; amountDate.getTime().before(new Date()); i++) {

            //проверка на активность КЛ
            if(creditDebt.isTerminateDate(amountDate.getTime(),account.getLimitTerminationDate())){
                creditLimit = 0;
            }else {
                if(creditLimit == 0){
                    creditLimit = 0;
                }else {
                    if(creditDebt.isDecreaseDate(amountDate.getTime(),account.getLimitDecreaseDate())){
                        creditLimit -= decreaseAmount;
                    }
                }
            }

            //если дата расчета равна дате платежа, тогда выбираем платеж
            if (creditDebt.isNowPaymentDate(amountDate.getTime(), payments)) {
                pay = creditDebt.getAmount(amountDate.getTime(), payments);
            } else {
                pay = 0;
            }

            //проверка даты расчета на биллинговую дату
            boolean isBilling = creditDebt.isBillingDate(amountDate.getTime());

            //% основной задолженности
            if(accessibleAmount>0){
                percentPrincipalDebt = debts * account.getPercentDebtDue() / 100 / 360;
            }else{
                percentPrincipalDebt = 0;
            }

            //% несанкционированного овера
            if(accessibleAmount<0){
                percentPastDueDebts = debts * account.getPercentPastDue() / 100 / 360;
            }else{
                percentPastDueDebts = 0;
            }

            //Сумма % основной задолженности
            if (isBilling) {
                sumPercentPrincipalDebt = 0;
            } else {
                sumPercentPrincipalDebt += percentPrincipalDebt;
            }
            //Сумма % несанкц. овера
            if (isBilling) {
                sumPercentPastDueDebts = 0;
            } else {
                sumPercentPastDueDebts += percentPastDueDebts;
            }
            percents[0][i] = sumPercentPrincipalDebt;
            percents[1][i] = sumPercentPastDueDebts;

            //выбраный платеж с учетом биллинга
            if(isBilling && i != 0){
                debts += pay + percents[0][i-1] + percents[1][i-1];
            }else {
                debts += pay;

            }

            //общая задолженность
            fullDebts = debts  + sumPercentPrincipalDebt  + sumPercentPastDueDebts;




            //формирование табл. для вывода
            DebtTable debtTable = new DebtTable();
            debtTable.setId(i+1);
            debtTable.setDate(simpleDateFormat.format(amountDate.getTime()));
            debtTable.setCreditLimit(creditDebt.getFormattedAmount(creditLimit));
            debtTable.setDebts(creditDebt.getFormattedAmount(debts));
            debtTable.setPay(creditDebt.getFormattedAmount(pay));
            debtTable.setPercentPrincipalDebt(creditDebt.getFormattedAmount(percentPrincipalDebt));
            debtTable.setPercentPastDueDebts(creditDebt.getFormattedAmount(percentPastDueDebts));
            debtTable.setSumPercentPrincipalDebt(creditDebt.getFormattedAmount(sumPercentPrincipalDebt));
            debtTable.setSumPercentPastDueDebts(creditDebt.getFormattedAmount(sumPercentPastDueDebts));
            debtTable.setFullDebts(creditDebt.getFormattedAmount(fullDebts));

            result.add(debtTable);

            //накручиваем плюс день
            amountDate.add(Calendar.DATE, 1);

        }

        return result;
    }
}
