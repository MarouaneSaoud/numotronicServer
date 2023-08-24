package org.numo.service.impl;

import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.*;
import org.numo.dao.repository.DeviceRepository;
import org.numo.dao.repository.ReferenceRepository;
import org.numo.dto.client.DeviceToClient;
import org.numo.dto.company.DeviceToCompany;
import org.numo.dto.device.DeviceToSave;
import org.numo.dto.device.DeviceToSend;
import org.numo.dto.device.DevicesFromAPI;
import org.numo.dto.groupeDevice.DeviceToGroup;
import org.numo.error.BusinessException;
import org.numo.error.TechnicalException;
import org.numo.functions.CalculateDeviceStatus;
import org.numo.functions.GetDevice;
import org.numo.functions.impl.GetDeviceFromApi;
import org.numo.model.StatusDevice;
import org.numo.service.ClientService;
import org.numo.service.CompanyService;
import org.numo.service.DeviceGroupService;
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
    private final ClientService clientService;
    private final DeviceGroupService deviceGroupService;


    @Override
    public List<DeviceToSend> devicelist() {
        GetDevice getDevice = new GetDeviceFromApi();
        List<DevicesFromAPI> devicesFromAPI = getDevice.AllDevices(); // add time out
        Set<String> activeImies = new HashSet<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<DeviceToSend> listDevices = new ArrayList<>();

        for (DevicesFromAPI devices : devicesFromAPI) {
            Device deviceByImei = deviceRepository.findDeviceByImei(devices.getIMEI());
            if (deviceByImei != null) {
                DeviceToSend device = new DeviceToSend();
                device.setId(deviceByImei.getId());
                device.setImei(devices.getIMEI());
                device.setTime(devices.getLastSeen());
                device.setFirmware(devices.getFirware());
                device.setConfiguration(devices.getConfig());
                if (deviceByImei.getCompany() != null) {
                    device.setCompany(deviceByImei.getCompany().getName());
                }
                CalculateDeviceStatus status = new CalculateDeviceStatus();
                device.setStatusDevice(status.calculateDeviceStatus(devices.getLastSeen(), currentDateTime));
                activeImies.add(devices.getIMEI());
                listDevices.add(device);
            }
        }

        for (Device d : deviceRepository.findAll()) {
            if (!activeImies.contains(d.getImei())) {
                DeviceToSend device = new DeviceToSend();
                device.setId(d.getId());
                device.setImei(d.getImei());
                device.setTime(null);
                device.setFirmware(null);
                device.setConfiguration(null);
                device.setStatusDevice(StatusDevice.INACTIF);
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
    public Device findDeviceByImei(String imei) {
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
    public Boolean decommissionDeviceToCompany(String imei) {
        Device device = deviceRepository.findDeviceByImei(imei);
        if (device==null)throw new BusinessException("Error");
        else {
            device.setCompany(null);
            device.setDeviceGroup(null);
            device.setClient(null);
            deviceRepository.save(device);
            return true;
        }

    }

    @Override
    public Boolean allocateDeviceToClient(DeviceToClient deviceToClient) {
        Client client = clientService.findById(deviceToClient.getClient());
        Device device = deviceRepository.findDeviceByImei(deviceToClient.getImei());
        if(client==null || device==null)throw new BusinessException("Error");
        else {
            device.setClient(client);
            deviceRepository.save(device);
            return true;
        }
    }

    @Override
    public Boolean decommissionDeviceToClient(String imei) {
        Device device = deviceRepository.findDeviceByImei(imei);
        if(device==null)throw new BusinessException("Error");
        else {
            device.setClient(null);
            deviceRepository.save(device);
            return true;
        }
    }

    @Override
    public Boolean allocateDeviceToGroup(DeviceToGroup deviceToGroup) {
        Device device= deviceRepository.findDeviceByImei(deviceToGroup.getImei());
        DeviceGroup deviceGroup= deviceGroupService.findDeviceGroupById(1L);
        if(device==null || deviceGroup==null  ){throw new BusinessException("Error");}
        else{
            device.setDeviceGroup(deviceGroup);
            deviceRepository.save(device);
            return true;
        }
    }
    @Override
    public Boolean removeDeviceFromGroup(String imei) {
        Device device = deviceRepository.findDeviceByImei(imei);
        if(device==null)throw new BusinessException("Error");
        else {
            device.setDeviceGroup(null);
            deviceRepository.save(device);
            return true;
        }
    }
}
