package org.sid.service;

import lombok.AllArgsConstructor;
import org.sid.entities.Device;
import org.sid.repositories.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
