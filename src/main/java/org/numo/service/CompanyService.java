package org.numo.service;

import org.numo.dao.entity.Client;
import org.numo.dao.entity.Company;
import org.numo.dao.entity.Device;
import org.numo.dao.entity.DeviceGroup;
import org.numo.dto.company.*;
import org.numo.dto.device.DeviceToSend;

import java.util.List;

public interface CompanyService {
    List<Company> company_list() ;
    Company addCompany(CompanyToSave companyToSave);
    List<DeviceToSend> findDevicesByCompany(Company company);
    List<DeviceGroup> findDeviceGroupsByCompany(Company company);
    List<Client> findClientsByCompany(Company company);

    List<DeviceGroup> findDeviceGroupsByIdCompany(String id);
    List<Client> findClientsByIdCompany(String id);
    void delete(String id);
    Company getCompanyForLoggedInUser(String email);
    Company getCompanyById(String id);
    Long countDevicesByCompany(Company company);
    Long countClientsByCompany(Company company);
    Long countDeviceGroupsByCompany(Company company);
    Long countCompany();
    List<DeviceGroupWithDeviceCountDTO> getDeviceGroupsWithDeviceCountByCompany(Company company);
    CompanyDeviceAllocatePercentage percentageOfAffectedDevices(Company company);
    CompanyStatistic companyStatistic(Company company);
    List<Company> getTop5CompaniesByDeviceCount();

 }
