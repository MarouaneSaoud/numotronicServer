package org.numo.service;

import org.numo.dao.entity.Client;
import org.numo.dao.entity.Company;
import org.numo.dao.entity.Device;
import org.numo.dto.client.ClientToSave;
import org.numo.dto.device.DeviceToSend;

import java.util.List;

public interface ClientService {
    List<Client> clientList();
    Client addClient (ClientToSave client);
    void deleteClient(String id);
    Long count();
    List<Device> findDevicesByClientId (String id);
    Client findById(String id);
    List<DeviceToSend> findDevicesByClient(Client client);
    Client getClientForLoggedInUser(String email);
    Long countClientDevices(Client client);

}
