package com.fordros.DAO;

import com.fordros.entity.Payment;
import com.fordros.persistence.HibernateUtil;
import org.hibernate.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fordros on 25.03.2016.
 */
public class PaymentsDAOImpl extends GenericDAOImpl <Payment, BigDecimal> implements PaymentsDAO {


    public List<Payment> findAllPaymentByAccId(Integer accId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT p FROM Payment p WHERE p.account = " + accId;
        Query query = HibernateUtil.getSession().createQuery(sql);
        payments = findMany(query);
        return payments;
    }
}
