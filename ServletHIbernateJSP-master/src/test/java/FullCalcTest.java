import com.fordros.entity.Account;
import com.fordros.entity.CreditDebt;
import com.fordros.entity.Payment;
import com.fordros.session.AccountManager;
import com.fordros.session.AccountManagerImpl;
import com.fordros.session.PaymentManager;
import com.fordros.session.PaymentManagerImpl;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Fordros on 13.04.2016.
 */
public class FullCalcTest {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    AccountManager accountManager = new AccountManagerImpl();
    PaymentManager paymentManager = new PaymentManagerImpl();
    CreditDebt creditDebt = new CreditDebt();

    @Test
    public void fullAccTest(){
        Account account = accountManager.findByAccNumber("26250127222980001");
        List<Payment> payments = paymentManager.findAllPaymentByAcc(account.getId());
        Calendar amountDate = Calendar.getInstance(); //new Calendar(2016, 1, 25);

        amountDate.set(2016, Calendar.FEBRUARY, 20);
        int debts;
        int pay = 0;
        int percentPrincipalDebt; // % основной задолженности на дату
        int percentPastDueDebts; // % просроченной задолженности на дату
        int sumPercentPrincipalDebt = 0;
        int sumPercentPastDueDebts = 0;
        int fullDebts;
        int creditLimit = account.getCreditLimit();
        //проверка на активность КЛ
        if(account.getLimitDecreaseDate().before(amountDate.getTime())){
            creditLimit = 0;
        }
        //если дата расчета равна дате платежа, тогда учитываем платеж
            if (creditDebt.isNowPaymentDate(amountDate.getTime(),payments)) {
                pay = creditDebt.getAmount(amountDate.getTime(),payments);
                debts = creditDebt.calcDebts(account.getBalance(),  pay);
            } else {
                debts = creditDebt.calcDebts(account.getBalance(), 0);
            }

            int[][] percents = new int[200][200];

//ОБЩИЙ ЦИКЛ

        for(int i = 0; amountDate.getTime().before(new Date()); i++) {
            //проверка на активность КЛ
            if(account.getLimitDecreaseDate().before(amountDate.getTime())){
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
                debts += pay + percents[0][i-1] + percents[1][i-1];
            }

            //Общая задолженность
            fullDebts = creditLimit - debts + sumPercentPrincipalDebt * (-1) + sumPercentPastDueDebts * (-1);


            System.out.println(i + ". " +simpleDateFormat.format(amountDate.getTime()) + " - " + creditDebt.getFormattedAmount(debts) + " - " + pay
                    + " - " + creditDebt.getFormattedAmount(percentPrincipalDebt) + " - " + creditDebt.getFormattedAmount(percentPastDueDebts) + " - " + isBilling
                    + " - " + creditDebt.getFormattedAmount(sumPercentPrincipalDebt) + " - " + sumPercentPastDueDebts + " - " + creditDebt.getFormattedAmount(fullDebts ));


        }


        }

}

