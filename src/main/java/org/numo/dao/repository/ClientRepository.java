package org.numo.dao.repository;

import org.numo.dao.entity.Client;
import org.numo.dao.entity.Company;
import org.numo.dao.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,String> {
    @Query("SELECT c.devices FROM Client c WHERE c.id = :clientId")
    List<Device> findDevicesByClientId(String clientId);
    Client findByAccount_Username(String username);
    @Query("SELECT d FROM Device d WHERE d.client = :client")
    List<Device> findDevicesByClient(Client client);

}
