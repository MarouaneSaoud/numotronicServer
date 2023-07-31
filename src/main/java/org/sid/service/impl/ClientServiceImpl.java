package org.sid.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.AppUser;
import org.sid.dao.entity.Client;
import org.sid.dao.entity.Company;
import org.sid.dao.repository.ClientRepository;
import org.sid.dao.repository.CompanyRepository;
import org.sid.dto.client.ClientToSave;
import org.sid.functions.GenerateRandomPassword;
import org.sid.service.AccountService;
import org.sid.service.ClientService;
import org.sid.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
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
        client.setId(UUID.randomUUID().toString());
        client.setName(clientToSave.getName());
        client.setAddress(clientToSave.getAddress());
        client.setPostalcode(clientToSave.getPostalCode());
        client.setEmail(clientToSave.getEmail());
        client.setCin(clientToSave.getCin());
        Company company = companyService.getCompanyById(clientToSave.getCompanyId());
        client.setCompany(company);
        GenerateRandomPassword grp= new GenerateRandomPassword();
        String mdp = grp.generateRandomPassword(8);
        AppUser appUser = accountService.saveUser(clientToSave.getEmail(), clientToSave.getName(), mdp, mdp);
        accountService.addRoleToUser(appUser.getUsername(),"CLIENT");
        client.setAccount(appUser);

        Client saved = clientRepository.save(client);
        return saved;
    }

    @Override
    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }
}
