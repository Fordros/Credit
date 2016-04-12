package com.fordros;

import com.fordros.DAO.AccountDAO;
import com.fordros.DAO.AccountDAOImpl;
import com.fordros.DAO.PaymentsDAO;
import com.fordros.DAO.PaymentsDAOImpl;
import com.fordros.entity.Account;
import com.fordros.entity.Payments;
import com.fordros.persistence.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Created by Fordros on 21.03.2016.
 */
public class EnterPoint {

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        GregorianCalendar calen = new GregorianCalendar(2017, 3, 12);

        AccountDAO accountDAO = new AccountDAOImpl();
        PaymentsDAO paymentsDAO = new PaymentsDAOImpl();

        Account account = new Account();

        account.setAccountNumber("26250116102980001");
        account.setBalance(1234);
        account.setCreditLimit(1000);
        account.setSsn(1234567891);
        account.setLimitTerminationDate(calen.getTime());
        account.setLimitDecreaseDate(calen.getTime());
        account.setPercentDebtDue(12);
        account.setPercentPastDue(60);


        Payments payment = new Payments();
        payment.setAccountNumber("26250116102980001");
        payment.setAmount(200);
        payment.setDatePayment(new Date());

        account.addPayments(payment);

        payment.setAccount(account);

        HibernateUtil.beginTransaction();

        accountDAO.save(account);
        paymentsDAO.save(payment);
        HibernateUtil.commitTransaction();

    }

}
