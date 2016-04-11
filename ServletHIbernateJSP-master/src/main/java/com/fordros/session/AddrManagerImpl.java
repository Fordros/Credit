package com.fordros.session;

import com.fordros.DAO.AddrDAO;
import com.fordros.DAO.AddrDAOImpl;
import com.fordros.entity.Addr;
import com.fordros.persistence.HibernateUtil;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fordros on 25.03.2016.
 */
public class AddrManagerImpl implements AddrManager {
    AddrDAO addrDAO = new AddrDAOImpl();

    public List<Addr> loadAllAddr() {
        List<Addr> allAddrs = new ArrayList<Addr>();
        try {
            HibernateUtil.beginTransaction();
            allAddrs = addrDAO.findAll(Addr.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Error in method loadAllAddr" + ex);
        }
        return allAddrs;
    }


    public void saveNewAddr(Addr addr) {
       try {
           HibernateUtil.beginTransaction();
           addrDAO.save(addr);
           HibernateUtil.commitTransaction();
       }catch (HibernateException ex){
           System.out.println("Error in method saveNewAddr");
       }


    }


    public void deleteAddr(Addr addr) {
        try {
            HibernateUtil.beginTransaction();
            addrDAO.delete(addr);
            HibernateUtil.commitTransaction();
        }catch (HibernateException ex){
            System.out.println("Error in method deleteAddr");
        }
    }
}
