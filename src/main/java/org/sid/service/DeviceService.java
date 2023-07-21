package org.sid.service;

import org.sid.dao.entity.Device;
import org.sid.dto.DeviceToSave;
import org.sid.dto.DeviceToSend;

import java.util.List;

public interface DeviceService {
    List<DeviceToSend> devicelist();
    Device addDevice(DeviceToSave deviceToSave);
    void delete(Long id);
    Device findDeviceByImei(Integer imei);
    Long countDevices ();
}
