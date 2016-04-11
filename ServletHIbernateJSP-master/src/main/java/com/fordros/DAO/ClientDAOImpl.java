package com.fordros.DAO;

import com.fordros.entity.Client;
import com.fordros.persistence.HibernateUtil;
import org.hibernate.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fordros on 22.03.2016.
 */
public class ClientDAOImpl  extends GenericDAOImpl<Client, BigDecimal> implements ClientDAO {

    public Client findByName(String name, String surname) {
        Client client = null;
        String sql = "SELECT p FROM Client p WHERE p.first_name = :name AND p.last_name = :surname";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("first_name", name).setParameter("last_name", surname);
        client = findOne(query);
        return client;
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        clients = getAllClients();
        return clients;
    }
}
