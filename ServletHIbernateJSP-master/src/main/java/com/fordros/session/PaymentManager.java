package com.fordros.session;

import com.fordros.entity.Payments;

import java.util.List;

/**
 * Created by Fordros on 25.03.2016.
 */
public interface PaymentManager {

    public List<Payments> loadAllPayment();

    public void saveNewPayment(Payments payments);

    public void deletePayment(Payments payments);
}
