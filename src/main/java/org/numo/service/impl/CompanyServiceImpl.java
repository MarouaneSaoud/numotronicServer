package org.numo.service.impl;

import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.*;
import org.numo.dao.repository.CompanyRepository;
import org.numo.dto.company.CompanyToSave;
import org.numo.error.BusinessException;
import org.numo.error.TechnicalException;
import org.numo.functions.GenerateRandomPassword;
import org.numo.service.AccountService;
import org.numo.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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
        try {

            Company company=new Company();

            GenerateRandomPassword grp= new GenerateRandomPassword();
            String mdp = grp.generateRandomPassword(8);

            AppUser appUser = accountService.saveUser(companyToSave.getEmail(), companyToSave.getName(), mdp, mdp);
            System.out.println(appUser);
            if (appUser==null) throw new BusinessException("User Not Created");
                accountService.addRoleToUser(company.getEmail(), "MANAGER");
                company.setAccount(appUser);

                company.setId(UUID.randomUUID().toString());
                company.setName(companyToSave.getName());
                company.setAltName(companyToSave.getAltName());
                company.setCin(companyToSave.getCin());
                company.setAddress(companyToSave.getAddress());
                company.setPostalCode(companyToSave.getPostalCode());
                company.setDepartment(companyToSave.getDepartment());
                company.setEmail(companyToSave.getEmail());
                company.setWebsite(companyToSave.getWebsite());
                company.setSkype(companyToSave.getSkype());
                company.setIdrc(companyToSave.getIdrc());
                company.setIdif(companyToSave.getIdif());
                company.setPatent(companyToSave.getPatent());
                company.setCnss(companyToSave.getCnss());
                company.setCountry(companyToSave.getCountry());
                company.setLogo(companyToSave.getLogo());

                company.setDeviceGroups(new ArrayList<>());
                company.setDevices(new ArrayList<>());
                company.setClients(new ArrayList<>());



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




}
