package com.fordros.servlet;


import com.fordros.entity.Account;
import com.fordros.entity.Payments;
import com.fordros.session.AccountManager;
import com.fordros.session.AccountManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Fordros on 20.03.2016.
 */

public class ClientServlet extends HttpServlet {
    AccountManager accountManager = new AccountManagerImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //List<Account> accounts = accountManager.loadAllAcc();
        //req.setAttribute("account", accounts);
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
        System.out.println(account);
        resp.sendRedirect("account");


    }

}