package org.numo.service;

import org.numo.dao.entity.DeviceGroup;
import org.numo.dto.groupeDevice.DeviceGroupToSave;

import java.util.List;

public interface DeviceGroupService {
    List<DeviceGroup> deviceGroups ();
    DeviceGroup addDeviceGroup(DeviceGroupToSave deviceGroupToSave);
    DeviceGroup findDeviceGroupById(Long id);
    void delete(Long id);
    List<DeviceGroup> findDeviceGroupsWithoutDevices();
    Long count();
}
