package org.sid.service.impl;

import lombok.AllArgsConstructor;
import org.sid.dao.entity.Device;
import org.sid.dao.repository.DeviceRepository;
import org.sid.dto.DevicesFromDTO;
import org.sid.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
@AllArgsConstructor
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private DeviceRepository deviceRepository;

    @Override
    public List<Device> devicelist() {

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl="https://api.thingspeak.com/channels/1053969/fields/1/last?api_key=TW9N2WQSI0AGG912";
        ResponseEntity<LinkedHashMap[]> responseEntity = restTemplate.getForEntity(apiUrl, LinkedHashMap[].class);
        LinkedHashMap[] usersArray = responseEntity.getBody();

        List<DevicesFromDTO> deviceList = new ArrayList<>();

        for (LinkedHashMap deviceMap : usersArray) {
            DevicesFromDTO devices = new DevicesFromDTO();
            devices.setImei((String) deviceMap.get("IMEI"));
            devices.setConfig((String) deviceMap.get("FIRMWARE"));
            devices.setReference((String) deviceMap.get("CONFIGUE"));
            devices.setLastSeen((String) deviceMap.get("TIME"));
            deviceList.add(devices);
        }
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
