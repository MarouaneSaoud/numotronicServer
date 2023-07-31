package org.sid.service;

import org.sid.dao.entity.Client;
import org.sid.dao.entity.Company;
import org.sid.dao.entity.Device;
import org.sid.dao.entity.DeviceGroup;
import org.sid.dto.company.CompanyToSave;
import org.sid.dto.user.FindUser;

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
