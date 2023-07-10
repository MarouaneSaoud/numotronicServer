package org.sid.repositories;

import org.sid.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeviceRepository extends JpaRepository<Device,Long> {
    Device findDeviceByImei(int imei);
}
