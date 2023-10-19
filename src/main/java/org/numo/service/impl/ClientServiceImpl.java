package org.numo.service.impl;

import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.AppUser;
import org.numo.dao.entity.Client;
import org.numo.dao.entity.Company;
import org.numo.dao.entity.Device;
import org.numo.dao.repository.ClientRepository;
import org.numo.dao.repository.DeviceRepository;
import org.numo.dto.client.ClientToSave;
import org.numo.dto.device.DeviceToSend;
import org.numo.dto.device.DevicesFromAPI;
import org.numo.error.BusinessException;
import org.numo.error.TechnicalException;
import org.numo.functions.CalculateDeviceStatus;
import org.numo.functions.GenerateRandomPassword;
import org.numo.functions.GetDevice;
import org.numo.service.AccountService;
import org.numo.service.ClientService;
import org.numo.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final CompanyService companyService;
    private final AccountService accountService;
    private final GetDevice getDevice ;
    private final DeviceRepository deviceRepository;


    @Override
    public List<Client> clientList() {
        List<Client> all = clientRepository.findAll();
        return all;
    }

    @Override
    public Client addClient(ClientToSave clientToSave) {

        Client client = new Client();
        Company companyForLoggedInUser = companyService.getCompanyForLoggedInUser(clientToSave.getCompanyEmail());
        Company company = companyService.getCompanyById(companyForLoggedInUser.getId());


        try {


            if (company!=null) {

                client.setId(UUID.randomUUID().toString());
                client.setName(clientToSave.getName());
                client.setAddress(clientToSave.getAddress());
                client.setPostalCode(clientToSave.getPostalCode());
                client.setEmail(clientToSave.getEmail());
                client.setCin(clientToSave.getCin());
                client.setCompany(company);

                AppUser appUser = accountService.saveUser(clientToSave.getEmail(), clientToSave.getName(), clientToSave.getPassword(), clientToSave.getPassword());
                accountService.addRoleToUser(appUser.getUsername(), "CLIENT");
                client.setAccount(appUser);

                Client saved = clientRepository.save(client);
                return saved;
            }else {
                throw new BusinessException("ERROR");

            }
        } catch (TechnicalException t){

                throw new TechnicalException("ERROR");
        }
    }

    @Override
    public void deleteClient(String id) {
        Client client = clientRepository.findById(id).orElse(null);
        List<Device> devicesByClientId = findDevicesByClientId(id);
        if(client!=null){
            accountService.delete(client.getAccount().getId());
            client.setCompany(null);
            for(Device d : devicesByClientId) d.setClient(null);
            clientRepository.deleteById(id);
        }
    }

    @Override
    public Long count() {
        return clientRepository.count();
    }
    @Override
    public List<Device> findDevicesByClientId(String id) {
        List<Device> devicesById = clientRepository.findDevicesByClientId(id);
        return devicesById;
    }

    @Override
    public Client findById(String id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<DeviceToSend> findDevicesByClient(Client client) {
        List<DevicesFromAPI> devicesFromAPI = getDevice.AllDevices();
        Set<String> activeImies = new HashSet<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<DeviceToSend> listDevices = new ArrayList<>();

        for (DevicesFromAPI devices : devicesFromAPI) {
            List<Device> devicesByCompany = clientRepository.findDevicesByClient(client);

            for (Device d : devicesByCompany) {
                Device deviceByImei = deviceRepository.findDeviceByImei(d.getImei());
                if (deviceByImei != null && devices.getIMEI().equals(d.getImei())) {
                    DeviceToSend device = new DeviceToSend();
                    device.setId(deviceByImei.getId());
                    device.setImei(devices.getIMEI());
                    device.setTime(devices.getLastSeen());
                    device.setFirmware(devices.getFirware());
                    device.setConfiguration(devices.getConfig());
                    if (deviceByImei.getCompany() != null) {
                        device.setCompany(deviceByImei.getCompany().getName());
                    }
                    if (deviceByImei.getClient() != null) {
                        device.setClient(deviceByImei.getClient().getName());
                    }

                    device.setGroup(null);

                    CalculateDeviceStatus status = new CalculateDeviceStatus();
                    device.setStatusDevice(status.calculateDeviceStatus(devices.getLastSeen(), currentDateTime));
                    activeImies.add(devices.getIMEI());
                    listDevices.add(device);
                }
            }
        }
        return listDevices;
    }

    @Override
    public Client getClientForLoggedInUser(String email) {
        return clientRepository.findByAccount_Username(email);
    }

    @Override
    public Long countClientDevices(Client client) {
        int size = clientRepository.findDevicesByClient(client).size();
        return (long) size;
    }


}
