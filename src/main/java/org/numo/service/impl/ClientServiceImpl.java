package org.numo.service.impl;

import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.AppUser;
import org.numo.dao.entity.Client;
import org.numo.dao.entity.Company;
import org.numo.dao.entity.Device;
import org.numo.dao.repository.ClientRepository;
import org.numo.dto.client.ClientToSave;
import org.numo.error.BusinessException;
import org.numo.error.TechnicalException;
import org.numo.functions.GenerateRandomPassword;
import org.numo.service.AccountService;
import org.numo.service.ClientService;
import org.numo.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService  {
    private final ClientRepository clientRepository;
    private final CompanyService companyService;
    private final AccountService accountService;

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

                GenerateRandomPassword grp = new GenerateRandomPassword();
                String mdp = grp.generateRandomPassword(8);
                AppUser appUser = accountService.saveUser(clientToSave.getEmail(), clientToSave.getName(), mdp, mdp);
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
}
