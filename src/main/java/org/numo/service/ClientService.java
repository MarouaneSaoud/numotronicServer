package org.numo.service;

import org.numo.dao.entity.Client;
import org.numo.dto.client.ClientToSave;

import java.util.List;

public interface ClientService {
    List<Client> clientList();
    Client addClient (ClientToSave client);
    void deleteClient(String id);
    Long cout();



}
