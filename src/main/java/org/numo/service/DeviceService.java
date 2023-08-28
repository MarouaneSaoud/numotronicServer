package org.numo.service;

import org.numo.dao.entity.Device;
import org.numo.dto.client.DeviceToClient;
import org.numo.dto.company.DeviceToCompany;
import org.numo.dto.device.DeviceToSave;
import org.numo.dto.device.DeviceToSend;
import org.numo.dto.groupeDevice.DeviceToGroup;

import java.util.Date;
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

    Boolean allocateDeviceToClient(DeviceToClient deviceToClient);
    Boolean decommissionDeviceToClient(String imei);
    Boolean allocateDeviceToGroup(DeviceToGroup deviceToGroup);
    Boolean removeDeviceFromGroup(String imei);


}
