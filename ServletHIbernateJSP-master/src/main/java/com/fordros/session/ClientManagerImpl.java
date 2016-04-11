package com.fordros.session;

import com.fordros.DAO.ClientDAO;
import com.fordros.DAO.ClientDAOImpl;
import com.fordros.entity.Client;
import com.fordros.persistence.HibernateUtil;
import org.hibernate.HibernateException;

import javax.persistence.NonUniqueResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fordros on 22.03.2016.
 */
public class ClientManagerImpl implements ClientManager {
    private ClientDAO clientDAO = new ClientDAOImpl();

    public Client findByClientName(String name, String surname) {
        Client client = null;
        try {
            HibernateUtil.beginTransaction();
            client = clientDAO.findByName(name, surname);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.out.println("Handle your error here");
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
        }
        return client;
    }

    public List<Client> loadAllClients() {
        List<Client> allClients = new ArrayList<Client>();
        try {
            HibernateUtil.beginTransaction();
            allClients = clientDAO.findAll(Client.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
        }
        return allClients;
    }

    public void saveNewClient(Client Client) {
        try {
            HibernateUtil.beginTransaction();
            clientDAO.save(Client);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here" + ex);
            HibernateUtil.rollbackTransaction();
        }
    }

    public Client findClientById(BigDecimal id) {
        Client client = null;
        try {
            HibernateUtil.beginTransaction();
            client = (Client) clientDAO.findByID(Client.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
        }
        return client;
    }

    public void deleteClient(Client Client) {
        try {
            HibernateUtil.beginTransaction();
            clientDAO.delete(Client);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
            HibernateUtil.rollbackTransaction();
        }
    }
}
