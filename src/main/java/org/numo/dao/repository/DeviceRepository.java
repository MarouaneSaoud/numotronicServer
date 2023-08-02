package org.numo.dao.repository;

import org.numo.dao.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findDeviceByImei(Integer imei);
    @Query("SELECT d FROM Device d WHERE d.company IS NULL")
    List<Device> findDevicesWithoutCompany();


}
