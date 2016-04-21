package com.fordros.servlet;


import com.fordros.CalculationDebts;
import com.fordros.entity.Account;
import com.fordros.entity.DebtTable;
import com.fordros.session.AccountManager;
import com.fordros.session.AccountManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Fordros on 20.03.2016.
 */

public class ClientServlet extends HttpServlet {
    AccountManager accountManager = new AccountManagerImpl();
    DebtTable debtTable = new DebtTable();
    CalculationDebts calculationDebts = new CalculationDebts();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //List<Account> accounts = accountManager.loadAllAcc();
        //req.setAttribute("account", account);
//        List<Account> account = accountManager.loadAllAcc();
//        System.out.println(account);
//        req.setAttribute("account", account);
        req.getRequestDispatcher("client.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding ("UTF-8");
//        String firstName = req.getParameter("firstName");
//        String lastName = req.getParameter("lastName");
//        String phone = req.getParameter("phone");
//
//        Account account = new Account();
//        account.setAccountNumber(req.getParameter("accNumber"));
//
////        payments.setCity(req.getParameter("city"));
//        payments.setStreet(req.getParameter("street"));
//        payments.setBuilding(req.getParameter("building"));
//        payments.setApartment(req.getParameter("apartment"));
//
//        Account account = new Account(firstName,lastName,phone, payments);
//        payments.setAccount(account);
//       accountManager.saveNewClient(account);

        Account account = accountManager.findByAccNumber(req.getParameter("accNumber"));
        if(account == null){
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }else {
            List<DebtTable> calcDebts = calculationDebts.getCalcDebdts(account.getAccountNumber());
            //resp.sendRedirect("account");
            req.setAttribute("calcDebts", calcDebts);
            req.setAttribute("fio", account.getFirstName() + " " + account.getLastName());
            req.setAttribute("ssn", account.getSsn());
            req.setAttribute("acc", account.getAccountNumber());
            req.setAttribute("numberDog", account.getNumberDog());
            req.setAttribute("dateDog", account.getDateDog());
            req.setAttribute("creditLimit", account.getCreditLimit());
            req.setAttribute("percentDebitDue", account.getPercentDebtDue());
            req.setAttribute("debts", calcDebts.get(calcDebts.size()-2).getFullDebts());


            req.getRequestDispatcher("client.jsp").forward(req, resp);
        }

    }

}