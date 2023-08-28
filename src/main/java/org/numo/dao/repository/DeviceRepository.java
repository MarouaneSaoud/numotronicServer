package org.numo.dao.repository;

import org.numo.dao.entity.Device;
import org.numo.dto.StatisticsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findDeviceByImei(String imei);
    @Query("SELECT d FROM Device d WHERE d.company IS NULL")
    List<Device> findDevicesWithoutCompany();


    @Query("SELECT " +
            "    MONTH(c.createdAt) AS month, " +
            "    COUNT(DISTINCT c) AS clientCount, " +
            "    COUNT(DISTINCT d) AS deviceCount, " +
            "    COUNT(DISTINCT co) AS companyCount " +
            "FROM " +
            "    Client c, Device d, Company co " +
            "WHERE " +
            "    c.createdAt >= :nineMonthsAgo AND " +
            "    d.createdAt >= :nineMonthsAgo AND " +
            "    co.createdAt >= :nineMonthsAgo " +
            "GROUP BY " +
            "    MONTH(c.createdAt)")
    List<Object[]> getStatistics(Date nineMonthsAgo);

}
