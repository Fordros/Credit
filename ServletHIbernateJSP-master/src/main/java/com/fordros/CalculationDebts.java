package com.fordros;

import com.fordros.entity.Account;
import com.fordros.entity.CreditDebt;
import com.fordros.entity.DebtTable;
import com.fordros.entity.Payment;
import com.fordros.session.AccountManager;
import com.fordros.session.AccountManagerImpl;
import com.fordros.session.PaymentManager;
import com.fordros.session.PaymentManagerImpl;

import java.lang.reflect.Array;
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

    public List<DebtTable> getCalcDebdts(String acc) {
        List<DebtTable> result = new ArrayList<>();

        int debts;
        int pay = 0;
        int percentPrincipalDebt; // % основной задолженности на дату
        int percentPastDueDebts; // % просроченной задолженности на дату
        int sumPercentPrincipalDebt = 0;
        int sumPercentPastDueDebts = 0;
        int fullDebts;

        Account account = accountManager.findByAccNumber(acc);
        List<Payment> payments = paymentManager.findAllPaymentByAcc(account.getId());
        Calendar amountDate = Calendar.getInstance();

        // дата с которой начинается расчет
        amountDate.set(2016, Calendar.FEBRUARY, 20);
        int creditLimit = account.getCreditLimit();

        //проверка на активность КЛ
        if (account.getLimitTerminationDate().before(amountDate.getTime())) {
            creditLimit = 0;
        }
        //если дата расчета равна дате платежа, тогда учитываем платеж
        if (creditDebt.isNowPaymentDate(amountDate.getTime(), payments)) {
            pay = creditDebt.getAmount(amountDate.getTime(), payments);
            debts = creditDebt.calcDebts(account.getBalance(), creditLimit, pay);
        } else {
            debts = creditDebt.calcDebts(account.getBalance(), creditLimit, 0);
        }

        int[][] percents = new int[200][200];

//ОБЩИЙ ЦИКЛ

        for (int i = 0; amountDate.getTime().before(new Date()); i++) {
            //проверка на активность КЛ
            if (account.getLimitTerminationDate().before(amountDate.getTime())) {
                creditLimit = 0;
            }
            amountDate.add(Calendar.DATE, 1);
            //Биллинг
            boolean isBilling = creditDebt.isBillingDate(amountDate.getTime());

            //% основной задолженности
            if (debts > 0) {
                percentPrincipalDebt = (debts - creditLimit) * account.getPercentDebtDue() / 360 / 100;
            } else {
                percentPrincipalDebt = creditLimit * account.getPercentDebtDue() / 100 / 360 * (-1);
            }
            //% несанкц. овера
            if (debts < 0) {
                percentPastDueDebts = debts * account.getPercentPastDue() / 360 / 100;
            } else {
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

            //задолженность
            if (creditDebt.isNowPaymentDate(amountDate.getTime(), payments)) {
                pay = creditDebt.getAmount(amountDate.getTime(), payments);
                debts += pay;
            } else {
                pay = 0;
            }
            if (isBilling) {
                debts += pay + percents[0][i - 1] + percents[1][i - 1];
            }

            //Общая задолженность
            fullDebts = creditLimit - debts + sumPercentPrincipalDebt * (-1) + sumPercentPastDueDebts * (-1);
            DebtTable debtTable = new DebtTable();
            debtTable.setId(i);
            debtTable.setDate(simpleDateFormat.format(amountDate.getTime()));
            debtTable.setDebts(creditDebt.getFormatedAmount(debts));
            debtTable.setPay(creditDebt.getFormatedAmount(pay));
            debtTable.setPercentPrincipalDebt(creditDebt.getFormatedAmount(percentPrincipalDebt));
            debtTable.setPercentPastDueDebts(creditDebt.getFormatedAmount(percentPastDueDebts));
            debtTable.setSumPercentPrincipalDebt(creditDebt.getFormatedAmount(sumPercentPrincipalDebt));
            debtTable.setSumPercentPastDueDebts(creditDebt.getFormatedAmount(sumPercentPastDueDebts));
            debtTable.setFullDebts(creditDebt.getFormatedAmount(fullDebts));

            result.add(debtTable);

        }

        return result;
    }
}
