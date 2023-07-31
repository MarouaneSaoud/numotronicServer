package org.sid.service;

import org.sid.dao.entity.Client;
import org.sid.dto.client.ClientToSave;

import java.util.List;

public interface ClientService {
    List<Client> clientList();
    Client addClient (ClientToSave client);
    void deleteClient(String id);



}
