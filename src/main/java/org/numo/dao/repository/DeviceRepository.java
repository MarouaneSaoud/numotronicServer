package org.numo.dao.repository;

import org.numo.dao.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findDeviceByImei(Integer imei);

}
