package org.sid.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.AppUser;
import org.sid.dao.entity.Company;
import org.sid.dao.entity.Device;
import org.sid.dao.entity.DeviceGroup;
import org.sid.dao.repository.CompanyRepository;
import org.sid.dto.company.CompanyToSave;
import org.sid.error.TechnicalException;
import org.sid.service.CompanyService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

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
            company.setDevices(new ArrayList<>());
            company.setDeviceGroups(new ArrayList<>());
            company.setClients(new ArrayList<>());

        return companyRepository.save(company);
        }
        catch (TechnicalException t){
            throw new TechnicalException("ERROR");
        }
    }

    @Override
    public List<Device> findDevicesByCompany(Company company) {
        return companyRepository.findDevicesByCompany(company);
    }

    @Override
    public List<DeviceGroup> findDeviceGroupsByCompany(Company company) {
        return companyRepository.findDeviceGroupsByCompany(company);

    }

    @Override
    public void delete(String id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company getCompanyForLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof AppUser) {
            AppUser loggedInUser = (AppUser) authentication.getPrincipal();
            String loggedInUserEmail = loggedInUser.getUsername();
            Company company = companyRepository.findByAccount_Username(loggedInUserEmail);
            return company;
        }
        return null;

    }

    @Override
    public Company getCompanyById(String id) {
        return companyRepository.findById(id).orElse(null);
    }
}
