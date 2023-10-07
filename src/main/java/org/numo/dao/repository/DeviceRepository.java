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

    @Query("SELECT" +
            "    MONTH(d.createdAt) AS month," +
            "    COUNT(DISTINCT c.id) AS clientCount," +
            "    COUNT(DISTINCT dev.id) AS deviceCount," +
            "    COUNT(DISTINCT co.id) AS companyCount FROM" +
            "    Device d\n" +
            "LEFT JOIN" +
            "    Client c ON MONTH(c.createdAt) = MONTH(d.createdAt) AND c.createdAt >= :nineMonthsAgo\n" +
            "LEFT JOIN" +
            "    Device dev ON MONTH(dev.createdAt) = MONTH(d.createdAt) AND dev.createdAt >= :nineMonthsAgo\n" +
            "LEFT JOIN" +
            "    Company co ON MONTH(co.createdAt) = MONTH(d.createdAt) AND co.createdAt >= :nineMonthsAgo\n" +
            "WHERE" +
            "    d.createdAt >= :nineMonthsAgo\n" +
            "GROUP BY\n" +
            "    MONTH(d.createdAt)")
    List<Object[]> getStatistics(Date nineMonthsAgo);





}
