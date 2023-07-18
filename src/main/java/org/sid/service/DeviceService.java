package org.sid.service;

import org.sid.dao.entity.Device;
import org.sid.dto.DeviceToSave;
import org.sid.dto.DeviceToSend;

import java.util.List;

public interface DeviceService {
    List<DeviceToSend> devicelist();
    public Device addDevice(DeviceToSave deviceToSave);
    public void delete(Long id);
    public Device findDeviceByImei(Integer imei);
}
