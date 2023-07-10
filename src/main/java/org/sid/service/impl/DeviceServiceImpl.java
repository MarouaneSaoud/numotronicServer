package org.sid.service.impl;

import lombok.AllArgsConstructor;
import org.sid.dao.entity.Device;
import org.sid.dao.repository.DeviceRepository;
import org.sid.service.DeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private DeviceRepository deviceRepository;

    @Override
    public List<Device> devicelist() {
        return deviceRepository.findAll();
    }

    @Override
    public Device AddDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }
}
