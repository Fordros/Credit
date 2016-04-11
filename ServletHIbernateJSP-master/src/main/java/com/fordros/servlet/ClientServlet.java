package com.fordros.servlet;


import com.fordros.entity.Addr;
import com.fordros.entity.Client;
import com.fordros.session.ClientManager;
import com.fordros.session.ClientManagerImpl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fordros on 20.03.2016.
 */

public class ClientServlet extends HttpServlet {
    ClientManager clientManager = new ClientManagerImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Client> clients = clientManager.loadAllClients();
        req.setAttribute("client", clients);
        req.getRequestDispatcher("client.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding ("UTF-8");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");

        Addr addr = new Addr();
        addr.setCountry(req.getParameter("country"));
        addr.setCity(req.getParameter("city"));
        addr.setStreet(req.getParameter("street"));
        addr.setBuilding(req.getParameter("building"));
        addr.setApartment(req.getParameter("apartment"));

        Client client = new Client(firstName,lastName,phone, addr);
        addr.setClient(client);
       clientManager.saveNewClient(client);

        resp.sendRedirect("client");


    }

}