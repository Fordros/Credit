package com.fordros.servlet;


import com.fordros.CalculationDebts;
import com.fordros.entity.Account;
import com.fordros.entity.CreditDebt;
import com.fordros.entity.DebtTable;
import com.fordros.session.AccountManager;
import com.fordros.session.AccountManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Fordros on 20.03.2016.
 */

public class ClientServlet extends HttpServlet {
    AccountManager accountManager = new AccountManagerImpl();
    DebtTable debtTable = new DebtTable();
    CalculationDebts calculationDebts = new CalculationDebts();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    CreditDebt creditDebt = new CreditDebt();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("client.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding ("UTF-8");

        Account account = accountManager.findByAccNumber(req.getParameter("accNumber"));
        if(account == null){
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }else {
            List<DebtTable> calcDebts = calculationDebts.getCalcDebts(account.getAccountNumber());
            //resp.sendRedirect("account");
            req.setAttribute("calcDebts", calcDebts);
            req.setAttribute("fio", account.getFirstName() + " " + account.getLastName() + " " + account.getMiddleName());
            req.setAttribute("ssn", account.getSsn());
            req.setAttribute("acc", account.getAccountNumber());
            req.setAttribute("numberDog", account.getNumberDog());
            req.setAttribute("dateDog", simpleDateFormat.format(account.getDateDog()));
            req.setAttribute("creditLimit", creditDebt.getFormatedAmount(account.getCreditLimit()));
            req.setAttribute("percentDebitDue", account.getPercentDebtDue());
            req.setAttribute("debts", calcDebts.get(calcDebts.size()-2).getFullDebts());

            req.getRequestDispatcher("client.jsp").forward(req, resp);
        }

    }

}