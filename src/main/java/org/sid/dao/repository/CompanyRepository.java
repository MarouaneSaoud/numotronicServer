package org.sid.dao.repository;

import org.sid.dao.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,String> {
    @Query("SELECT d FROM Device d WHERE d.company = :company")
    List<Device> findDevicesByCompany(Company company);
    @Query("SELECT d FROM DeviceGroup d WHERE d.company = :company")
    List<DeviceGroup> findDeviceGroupsByCompany(Company company);
    @Query("SELECT c FROM Client c WHERE c.company = :company")
    List<Client> findClientsByCompany(Company company);
    Company findByAccount_Username(String username);
    @Query("SELECT COUNT(d) FROM Device d WHERE d.company = :company")
    long countDevicesByCompany(Company company);
    @Query("SELECT COUNT(c) FROM Client c WHERE c.company = :company")
    long countClientsByCompany(Company company);
    @Query("SELECT COUNT(dg) FROM DeviceGroup dg WHERE dg.company = :company")
    long countDeviceGroupsByCompany(Company company);

}
