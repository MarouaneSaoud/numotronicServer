package org.numo.service;

import org.numo.dao.entity.Client;
import org.numo.dao.entity.Company;
import org.numo.dao.entity.Device;
import org.numo.dao.entity.DeviceGroup;
import org.numo.dto.company.CompanyToSave;

import java.util.List;

public interface CompanyService {
    List<Company> company_list() ;
    Company addCompany(CompanyToSave companyToSave);
    List<Device> findDevicesByCompany(Company company);
    List<DeviceGroup> findDeviceGroupsByCompany(Company company);
    List<Client> findClientsByCompany(Company company);
    void delete(String id);
    Company getCompanyForLoggedInUser(String email);
    Company getCompanyById(String id);
    Long countDevicesByCompany(Company company);
    Long countClientsByCompany(Company company);
    Long countDeviceGroupsByCompany(Company company);
    Long countCompany();

 }
