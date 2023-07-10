package org.sid.service;

import org.sid.entities.Device;

import java.util.List;

public interface DeviceService {
    List<Device> devicelist();
    public Device AddDevice(Device device);
    public void delete(Long id);
}
