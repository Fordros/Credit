package com.fordros.DAO;

import com.fordros.entity.Account;

import java.math.BigDecimal;

/**
 * Created by Fordros on 22.03.2016.
 */
public interface AccountDAO extends GenericDAO <Account, BigDecimal>{
    public Account findByAccNumber(String accNumber);
}
