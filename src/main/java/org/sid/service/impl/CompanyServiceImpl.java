package org.sid.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.*;
import org.sid.dao.repository.CompanyRepository;
import org.sid.dto.company.CompanyToSave;
import org.sid.error.TechnicalException;
import org.sid.service.AccountService;
import org.sid.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final AccountService accountService;

    @Override
    public List<Company> company_list() {
        return companyRepository.findAll();
    }
    @Override
    public Company addCompany(CompanyToSave companyToSave) {
        Company company=new Company();
        try {
            company.setId(UUID.randomUUID().toString());
            company.setName(companyToSave.getName());
            company.setAltname(companyToSave.getAltname());
            company.setCin(companyToSave.getCin());
            company.setAddress(companyToSave.getAddress());
            company.setPostalcode(companyToSave.getPostalcode());
            company.setDepartement(companyToSave.getDepartement());
            company.setEmail(companyToSave.getEmail());
            company.setWebsite(companyToSave.getWebsite());
            company.setSkype(companyToSave.getSkype());
            company.setIdrc(companyToSave.getIdrc());
            company.setIdif(companyToSave.getIdif());
            company.setPatent(companyToSave.getPatent());
            company.setCnss(companyToSave.getCnss());
            company.setCountry(companyToSave.getCountry());
            company.setLogo(companyToSave.getLogo());
            company.setDevices(new HashSet<>());
            company.setDeviceGroups(new HashSet<>());
            company.setClients(new HashSet<>());

            String mdp = generateRandomPassword(8);
            System.out.println(mdp);
            AppUser appUser = accountService.saveUser(company.getEmail(), company.getName(), mdp, mdp);
            accountService.addRoleToUser(appUser.getUsername(),"MANAGER");

            company.setAccount(appUser);

        return companyRepository.save(company);
        }
        catch (TechnicalException t){
            throw new TechnicalException("ERROR");
        }
    }

    @Override
    public List<Device> findDevicesByCompany(Company company) {
        List<Device> devicesByCompany = companyRepository.findDevicesByCompany(company);
        return devicesByCompany;
    }

    @Override
    public List<DeviceGroup> findDeviceGroupsByCompany(Company company) {
        List<DeviceGroup> deviceGroupsByCompany = companyRepository.findDeviceGroupsByCompany(company);
        return deviceGroupsByCompany;

    }
    @Override
    public List<Client> findClientsByCompany(Company company) {
        List<Client> clientsByCompany = companyRepository.findClientsByCompany(company);
        return clientsByCompany;
    }
    @Override
    public void delete(String id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company getCompanyForLoggedInUser(String email) {
            Company company = companyRepository.findByAccount_Username(email);
            return company;
    }

    @Override
    public Company getCompanyById(String id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Long countDevicesByCompany(Company company) {
        long l = companyRepository.countDevicesByCompany(company);
        return l;
    }

    @Override
    public Long countClientsByCompany(Company company) {
        long l = companyRepository.countClientsByCompany(company);
        return l;
    }

    @Override
    public Long countDeviceGroupsByCompany(Company company) {
        return companyRepository.countDeviceGroupsByCompany(company);
    }

    @Override
    public Long countCompany() {
        return companyRepository.count();
    }


    public static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

}
