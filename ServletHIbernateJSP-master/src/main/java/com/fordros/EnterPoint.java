package com.fordros;

import com.fordros.DAO.AccountDAO;
import com.fordros.DAO.AccountDAOImpl;
import com.fordros.entity.Account;
import com.fordros.entity.Payment;
import com.fordros.persistence.HibernateUtil;

import java.util.GregorianCalendar;

/**
 * Created by Fordros on 21.03.2016.
 */
public class EnterPoint {

    public static void main(String[] args) {
        GregorianCalendar calen = new GregorianCalendar(2021, 0, 3);
        GregorianCalendar paymentDate = new GregorianCalendar(2016, 2, 1);

        AccountDAO accountDAO = new AccountDAOImpl();

        Account account = new Account();

        account.setAccountNumber("26250127222980001");
        account.setBalance(-126671);
        account.setCreditLimit(156000);
        account.setSsn(1234567891);
        account.setLimitTerminationDate(calen.getTime());
        account.setLimitDecreaseDate(calen.getTime());
        account.setPercentDebtDue(12);
        account.setPercentPastDue(60);


        Payment payment = new Payment();
        payment.setAccountNumber("26250127222980001");
        payment.setAmount(20000);
        payment.setDatePayment(paymentDate.getTime());
        account.addPayments(payment);

        Payment payment1 = new Payment();
        payment1.setAccountNumber("26250127222980001");
        payment1.setAmount(10000);
        payment1.setDatePayment(paymentDate.getTime());

        account.addPayments(payment1);

        HibernateUtil.beginTransaction();
        accountDAO.save(account);
        HibernateUtil.commitTransaction();

    }

}
