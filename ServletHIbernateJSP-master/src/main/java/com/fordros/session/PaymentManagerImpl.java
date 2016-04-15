package com.fordros.session;

import com.fordros.DAO.PaymentsDAO;
import com.fordros.DAO.PaymentsDAOImpl;
import com.fordros.entity.Payment;
import com.fordros.persistence.HibernateUtil;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fordros on 25.03.2016.
 */
public class PaymentManagerImpl implements PaymentManager {
    PaymentsDAO paymentsDAO = new PaymentsDAOImpl();

    public List<Payment> loadAllPayment() {
        List<Payment> allPaymentses = new ArrayList<Payment>();
        try {
            HibernateUtil.beginTransaction();
            allPaymentses = paymentsDAO.findAll(Payment.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Error in method loadAllPayment - " + ex);
        }
        return allPaymentses;
    }


    public List<Payment> findAllPaymentByAcc(Integer idAccount) {
        List<Payment> allPaymentByAcc = new ArrayList<Payment>();
        try {
            HibernateUtil.beginTransaction();
            allPaymentByAcc = paymentsDAO.findAllPaymentByAccId(idAccount);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Error in method findAllPaymentByAcc -" + ex);
        }
        return allPaymentByAcc;
    }


    public void saveNewPayment(Payment payment) {
       try {
           HibernateUtil.beginTransaction();
           paymentsDAO.save(payment);
           HibernateUtil.commitTransaction();
       }catch (HibernateException ex){
           System.out.println("Error in method saveNewPayment");
       }


    }


    public void deletePayment(Payment payment) {
        try {
            HibernateUtil.beginTransaction();
            paymentsDAO.delete(payment);
            HibernateUtil.commitTransaction();
        }catch (HibernateException ex){
            System.out.println("Error in method deletePayment");
        }
    }
}
