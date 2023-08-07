package org.numo.service;

import org.numo.dao.entity.Device;
import org.numo.dto.company.DeviceToCompany;
import org.numo.dto.device.DeviceToSave;
import org.numo.dto.device.DeviceToSend;

import java.util.List;

public interface DeviceService {
    List<DeviceToSend> devicelist();
    Device addDevice(DeviceToSave deviceToSave);
    void delete(Long id);
    Device findDeviceByImei(String imei);
    Long countDevices ();
    List<Device> findDevicesWithoutCompany();
    Boolean allocateDeviceToCompany(DeviceToCompany deviceToCompany);
    Boolean decommissionDeviceToCompany(String imei);
}
