package com.fordros.DAO;

import com.fordros.entity.Client;

import java.math.BigDecimal;

/**
 * Created by Fordros on 22.03.2016.
 */
public interface ClientDAO extends GenericDAO <Client, BigDecimal>{
    public Client findByName(String firstName, String lastName);
}
