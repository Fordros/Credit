package com.fordros.session;

import com.fordros.DAO.PaymentsDAO;
import com.fordros.DAO.PaymentsDAOImpl;
import com.fordros.entity.Payments;
import com.fordros.persistence.HibernateUtil;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fordros on 25.03.2016.
 */
public class PaymentManagerImpl implements PaymentManager {
    PaymentsDAO paymentsDAO = new PaymentsDAOImpl();

    public List<Payments> loadAllPayment() {
        List<Payments> allPaymentses = new ArrayList<Payments>();
        try {
            HibernateUtil.beginTransaction();
            allPaymentses = paymentsDAO.findAll(Payments.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Error in method loadAllPayment - " + ex);
        }
        return allPaymentses;
    }


    public List<Payments> findAllPaymentByAcc(Integer idAccount) {
        List<Payments> allPaymentsByAcc = new ArrayList<Payments>();
        try {
            HibernateUtil.beginTransaction();
            allPaymentsByAcc = paymentsDAO.findAllPaymentByAccId(idAccount);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Error in method findAllPaymentByAcc -" + ex);
        }
        return allPaymentsByAcc;
    }


    public void saveNewPayment(Payments payments) {
       try {
           HibernateUtil.beginTransaction();
           paymentsDAO.save(payments);
           HibernateUtil.commitTransaction();
       }catch (HibernateException ex){
           System.out.println("Error in method saveNewPayment");
       }


    }


    public void deletePayment(Payments payments) {
        try {
            HibernateUtil.beginTransaction();
            paymentsDAO.delete(payments);
            HibernateUtil.commitTransaction();
        }catch (HibernateException ex){
            System.out.println("Error in method deletePayment");
        }
    }
}
