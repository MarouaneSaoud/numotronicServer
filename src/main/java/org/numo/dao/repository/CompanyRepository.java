package org.numo.dao.repository;

import org.numo.dao.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
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
    @Query("SELECT dg, COUNT(dev) FROM DeviceGroup dg LEFT JOIN dg.devices dev WHERE dg.company = :company GROUP BY dg")
    List<Object[]> findDeviceGroupsWithDeviceCountByCompany(@Param("company") Company company);
    @Query("SELECT c FROM Company c " +
            "JOIN Device d ON c.id = d.company.id " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(d.id) DESC")
    List<Company> findTop5CompaniesByDeviceCount();
    @Query("SELECT c.clients " +
            "FROM Company c " +
            "WHERE c.id = :companyId " +
            "AND c.id IN (" +
            "SELECT cc.id " +
            "FROM Company cc " +
            "JOIN cc.clients client " +
            "GROUP BY cc.id " +
            "ORDER BY SIZE(client.devices) DESC" +
            ")")
    List<Client> findTop5ClientsWithMostDevices(@Param("companyId") String companyId);




}
