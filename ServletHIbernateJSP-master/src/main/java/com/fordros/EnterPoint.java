package com.fordros;

import com.fordros.DAO.ClientDAO;
import com.fordros.DAO.ClientDAOImpl;
import com.fordros.entity.Addr;
import com.fordros.entity.Client;
import com.fordros.persistence.HibernateUtil;

/**
 * Created by Fordros on 21.03.2016.
 */
public class EnterPoint {

    public static void main(String[] args) {
//        ClientManager clientManager = new ClientManagerImpl();
//        Client client = new Client();
//        client.setAccountNumber("Владимир");
//        client.setBalance("Воля");
//        client.setCreditLimit("0675309696");
//        clientManager.saveNewClient(client);

//        List<Client> clients = clientManager.loadAllClients();
//        System.out.println(clients);

//        AddrManager addrManager = new AddrManagerImpl();
//        Addr addr  = new Addr();
//        addr.setCountry("Украина");
//        addr.setCity("Кременуг");
//        addr.setStreet("Тельмана");
//        addr.setBuilding("28");
//        addr.setApartment("63");
//
//        //addrManager.saveNewAddr(addr);
//        System.out.println(addr);
//
//        List<Addr> addrs = addrManager.loadAllAddr();
//      System.out.println(addrs);
        ClientDAO clientDAO = new ClientDAOImpl();


        Client client = new Client();
        client.setAccountNumber("Ростислав");
        client.setBalance("Иващенко");
        client.setCreditLimit("0675404060");

        Addr addr = new Addr();
        addr.setCountry("Украина");
        addr.setCity("Кременуг");
        addr.setStreet("Тельмана");
        addr.setBuilding("28");
        addr.setApartment("63");

        client.setAddr(addr);
        addr.setClient(client);

        HibernateUtil.beginTransaction();
        clientDAO.save(client);
        HibernateUtil.commitTransaction();
    }

}
