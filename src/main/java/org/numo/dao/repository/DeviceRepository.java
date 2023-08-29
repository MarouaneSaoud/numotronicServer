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
            "    MONTH(d.createdAt) AS month, " +
            "    (SELECT COUNT(c) FROM Client c WHERE MONTH(c.createdAt) = MONTH(d.createdAt) AND c.createdAt >= :nineMonthsAgo) AS clientCount, " +
            "    (SELECT COUNT(dev) FROM Device dev WHERE MONTH(dev.createdAt) = MONTH(d.createdAt) AND dev.createdAt >= :nineMonthsAgo) AS deviceCount, " +
            "    (SELECT COUNT(co) FROM Company co WHERE MONTH(co.createdAt) = MONTH(d.createdAt) AND co.createdAt >= :nineMonthsAgo) AS companyCount " +
            "FROM " +
            "    Device d " +
            "WHERE " +
            "    d.createdAt >= :nineMonthsAgo " +
            "GROUP BY " +
            "    MONTH(d.createdAt)")
    List<Object[]> getStatistics(Date nineMonthsAgo);




}
