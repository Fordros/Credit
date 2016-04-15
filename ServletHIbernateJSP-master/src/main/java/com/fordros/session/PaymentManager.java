package com.fordros.session;

import com.fordros.entity.Payment;

import java.util.List;

/**
 * Created by Fordros on 25.03.2016.
 */
public interface PaymentManager {

    public List<Payment> loadAllPayment();

    public List<Payment> findAllPaymentByAcc(Integer idAccount);

    public void saveNewPayment(Payment payment);

    public void deletePayment(Payment payment);
}
