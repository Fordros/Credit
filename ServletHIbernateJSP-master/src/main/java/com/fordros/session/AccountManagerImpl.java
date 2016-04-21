package com.fordros.session;

import com.fordros.DAO.AccountDAO;
import com.fordros.DAO.AccountDAOImpl;
import com.fordros.entity.Account;
import com.fordros.persistence.HibernateUtil;
import org.hibernate.HibernateException;

import javax.persistence.NonUniqueResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fordros on 22.03.2016.
 */
public class AccountManagerImpl implements AccountManager {
    private AccountDAO accountDAO = new AccountDAOImpl();

    public Account findByAccNumber(String accNumber) {
        Account account = null;
        try {
            HibernateUtil.beginTransaction();
            account = accountDAO.findByAccNumber(accNumber);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.out.println("Query returned more than one results." + ex);
        } catch (HibernateException ex) {
            System.out.println("Error in method 'findByAccNumber'" + ex );
        }
        return account;
    }

    public List<Account> loadAllAcc() {
        List<Account> allAccounts = new ArrayList<Account>();
        try {
            HibernateUtil.beginTransaction();
            allAccounts = accountDAO.findAll(Account.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Error in method 'loadAllAcc'");
        }
        return allAccounts;
    }

    public void saveNewAcc(Account Account) {
        try {
            HibernateUtil.beginTransaction();
            accountDAO.save(Account);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Error in method 'saveNewAccount'" + ex);
            HibernateUtil.rollbackTransaction();
        }
    }

    public Account findAccById(Integer id) {
        Account account = null;
        try {
            HibernateUtil.beginTransaction();
            account = (Account) accountDAO.findByID(Account.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
        }
        return account;
    }

    public void deleteAcc(Account Account) {
        try {
            HibernateUtil.beginTransaction();
            accountDAO.delete(Account);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
            HibernateUtil.rollbackTransaction();
        }
    }
}
