package org.numo.dao.repository;

import org.numo.dao.entity.DeviceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceGroupRepository extends JpaRepository<DeviceGroup , Long> {
    @Query("SELECT dg FROM DeviceGroup dg WHERE NOT EXISTS (SELECT 1 FROM Device d WHERE d.deviceGroup = dg)")
    List<DeviceGroup> findDeviceGroupsWithoutDevices();

}
