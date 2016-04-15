package com.fordros.DAO;

import com.fordros.entity.Payment;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Fordros on 25.03.2016.
 */
public interface PaymentsDAO extends GenericDAO <Payment, BigDecimal> {
    public List<Payment> findAllPaymentByAccId(Integer accId);
}
