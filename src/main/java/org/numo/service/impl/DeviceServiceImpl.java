package org.numo.service.impl;

import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.Company;
import org.numo.dao.entity.Device;
import org.numo.dao.entity.Reference;
import org.numo.dao.repository.DeviceRepository;
import org.numo.dao.repository.ReferenceRepository;
import org.numo.dto.company.DeviceToCompany;
import org.numo.dto.device.DeviceToSave;
import org.numo.dto.device.DeviceToSend;
import org.numo.dto.device.DevicesFromAPI;
import org.numo.error.BusinessException;
import org.numo.error.TechnicalException;
import org.numo.functions.GetDevice;
import org.numo.functions.impl.GetDeviceFromApi;
import org.numo.model.StatusDevice;
import org.numo.service.CompanyService;
import org.numo.service.DeviceService;
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
    private final CompanyService companyService;


    @Override
    public List<DeviceToSend> devicelist() {
        GetDevice getDevice= new GetDeviceFromApi();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");
        List<DevicesFromAPI> devicesFromAPI = getDevice.AllDevices();
        List<DeviceToSend> listDevices =  new ArrayList<>();
        for (DevicesFromAPI devices : devicesFromAPI) {

            Device deviceByImei = deviceRepository.findDeviceByImei(Integer.parseInt(devices.getIMEI()));
                if (deviceByImei!=null ){
                    DeviceToSend device= new DeviceToSend();
                    device.setId(deviceByImei.getId());
                    device.setImei(Integer.parseInt(devices.getIMEI()));
                    device.setTime(devices.getLastSeen());
                    device.setFirmware(devices.getFirware());
                    device.setConfiguration(devices.getConfig());
                    if (deviceByImei.getCompany()!=null) device.setCompany(deviceByImei.getCompany().getName());
                    else device.setCompany(null);
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

    @Override
    public List<Device> findDevicesWithoutCompany() {
        return deviceRepository.findDevicesWithoutCompany();
    }

    @Override
    public Boolean allocateDeviceToCompany(DeviceToCompany deviceToCompany) {
        Company company= companyService.getCompanyById(deviceToCompany.getCompany());
        Device device = deviceRepository.findDeviceByImei(deviceToCompany.getImei());
        if(company==null || device==null)throw new BusinessException("Error");
        else {
            device.setCompany(company);
            deviceRepository.save(device);
            return true;
        }
    }

    @Override
    public Boolean decommissionDeviceToCompany(int imei) {
        Device device = deviceRepository.findDeviceByImei(imei);
        if (device==null)throw new BusinessException("Error");
        else {
            device.setCompany(null);
            device.setDeviceGroup(null);
            deviceRepository.save(device);
            return true;
        }

    }
}
