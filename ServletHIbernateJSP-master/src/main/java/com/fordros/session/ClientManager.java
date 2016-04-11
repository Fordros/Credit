package com.fordros.session;


import com.fordros.entity.Client;

import java.math.BigDecimal;
import java.util.List;
/**
 * Created by Fordros on 22.03.2016.
 */
public interface ClientManager {
    public Client findByClientName(String name, String surname);

    public List<Client> loadAllClients();

    public void saveNewClient(Client Client);

    public Client findClientById(BigDecimal id);

    public void deleteClient(Client Client);
}
