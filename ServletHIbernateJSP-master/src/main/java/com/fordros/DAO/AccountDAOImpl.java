package com.fordros.DAO;

import com.fordros.entity.Account;
import com.fordros.persistence.HibernateUtil;
import org.hibernate.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fordros on 12.04.2016.
 */
public class AccountDAOImpl extends GenericDAOImpl<Account, BigDecimal> implements AccountDAO {

    public Account findByAccNumber(String accNumber) {
        Account account = null;
        String sql = "SELECT a FROM Account a WHERE a.accountNumber = :accNumber";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("accNumber", accNumber);
        account = findOne(query);
        return account;
    }

    public List<Account> getAllAcc() {
        List<Account> accounts = new ArrayList<>();
        accounts = getAllAcc();
        return accounts;
    }

}
