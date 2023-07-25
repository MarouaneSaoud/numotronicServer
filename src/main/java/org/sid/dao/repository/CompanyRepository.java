package org.sid.dao.repository;

import org.sid.dao.entity.AppUser;
import org.sid.dao.entity.Company;
import org.sid.dao.entity.Device;
import org.sid.dao.entity.DeviceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,String> {
    @Query("SELECT d FROM Device d WHERE d.company = :company")
    List<Device> findDevicesByCompany(Company company);
    @Query("SELECT d FROM DeviceGroup d WHERE d.company = :company")
    List<DeviceGroup> findDeviceGroupsByCompany(Company company);
    Company findByAccount_Username(String username);

}
