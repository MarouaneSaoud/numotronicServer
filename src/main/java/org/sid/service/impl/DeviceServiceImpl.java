package org.sid.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.Device;
import org.sid.dao.entity.Reference;
import org.sid.dao.repository.DeviceRepository;
import org.sid.dao.repository.ReferenceRepository;
import org.sid.dto.device.DeviceToSave;
import org.sid.dto.device.DeviceToSend;
import org.sid.dto.device.DevicesFromAPI;
import org.sid.error.TechnicalException;
import org.sid.functions.GetDevice;
import org.sid.functions.impl.GetDeviceFromApi;
import org.sid.model.StatusDevice;
import org.sid.service.DeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final ReferenceRepository referenceRepository;


    @Override
    public List<DeviceToSend> devicelist() {
        GetDevice getDevice= new GetDeviceFromApi();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");
        List<DevicesFromAPI> devicesFromAPI = getDevice.AllDevices();
        List<DeviceToSend> listDevices =  new ArrayList<>();
        for (DevicesFromAPI devices : devicesFromAPI){

                Device deviceByImei=deviceRepository.findDeviceByImei(Integer.parseInt(devices.getIMEI()));
                if (deviceByImei!=null){
                    DeviceToSend device= new DeviceToSend();
                device.setId(deviceByImei.getId());
                device.setImei(Integer.parseInt(devices.getIMEI()));
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
                listDevices.add(device);
            }

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
        Device device = deviceRepository.findDeviceByImei(imei);
        return device;
    }

    @Override
    public Long countDevices() {
        return deviceRepository.count();
    }
}
