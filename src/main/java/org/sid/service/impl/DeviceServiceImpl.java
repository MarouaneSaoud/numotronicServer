package org.sid.service.impl;

import lombok.AllArgsConstructor;
import org.sid.dao.entity.Device;
import org.sid.dao.entity.Reference;
import org.sid.dao.repository.DeviceRepository;
import org.sid.dao.repository.ReferenceRepository;
import org.sid.dto.device.DeviceToSave;
import org.sid.dto.device.DeviceToSend;
import org.sid.dto.device.DevicesFromDTO;
import org.sid.error.TechnicalException;
import org.sid.model.StatusDevice;
import org.sid.service.DeviceService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

@AllArgsConstructor
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private DeviceRepository deviceRepository;
    private ReferenceRepository referenceRepository;

    @Override
    public List<DeviceToSend> devicelist() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://gps-api-4beb.onrender.com/api/regrouped-data";
        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {});
        Map<String, Object> responseBody = responseEntity.getBody();

        List<Map<String, Object>> devicesList = (List<Map<String, Object>>) responseBody.get("devices");
        List<DevicesFromDTO> deviceListDTO = new ArrayList<>();

        for (Map<String, Object> deviceMap : devicesList) {
            DevicesFromDTO devices = new DevicesFromDTO();
            devices.setIMEI((String) deviceMap.get("imei"));
            devices.setFirware((String) deviceMap.get("firmware"));
            devices.setConfig((String) deviceMap.get("config"));
            devices.setLastSeen((String) deviceMap.get("lastSeen"));
            deviceListDTO.add(devices);
        }
        List<DeviceToSend> listDevices =  new ArrayList<>();
        for (DevicesFromDTO devices : deviceListDTO){
                DeviceToSend device= new DeviceToSend();
                if (deviceRepository.findDeviceByImei(Integer.parseInt(devices.getIMEI()))==null){
                device.setId(device.getId());
                device.setImei(devices.getIMEI());
                device.setTime(devices.getLastSeen());
                device.setFirmware(devices.getFirware());
                device.setConfiguration(devices.getConfig());
                try {
                    LocalDateTime lastSeenDateTime = LocalDateTime.parse(devices.getLastSeen(), formatter);
                    LocalDateTime currentDateTime = LocalDateTime.now();

                    if (ChronoUnit.HOURS.between(lastSeenDateTime, currentDateTime) <= 6) {
                        device.setStatusDevice(StatusDevice.ONLINE);
                    } else if (ChronoUnit.HOURS.between(lastSeenDateTime, currentDateTime) >= 6){
                        device.setStatusDevice(StatusDevice.OFFLINE);
                    }
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
            }
            listDevices.add(device);
        }

        return listDevices;
    }

    @Override
    public Device addDevice(DeviceToSave deviceToSave) {
        Device device = new Device();
        Reference reference = referenceRepository.findById(deviceToSave.getReference()).orElse(null) ;
        if (reference!=null) {

            device.setImei(deviceToSave.getImei());
            device.setSerialNum(deviceToSave.getSerialNum());
            device.setDescription(deviceToSave.getDescription());
            device.setCreatedAt(new Date());
            device.setReference(reference);

        }
        else {
            throw new TechnicalException("reference null");
        }

        return deviceRepository.save(device);

    }

    @Override
    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public Device findDeviceByImei(Integer imei) {
        return deviceRepository.findDeviceByImei(imei);
    }

    @Override
    public Long countDevices() {
        return deviceRepository.count();
    }
}
