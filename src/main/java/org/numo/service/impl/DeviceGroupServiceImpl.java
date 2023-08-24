package org.numo.service.impl;

import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.Company;
import org.numo.dao.entity.Device;
import org.numo.dao.entity.DeviceGroup;
import org.numo.dao.repository.DeviceGroupRepository;
import org.numo.dao.repository.DeviceRepository;
import org.numo.dto.device.DeviceDto;
import org.numo.dto.device.DeviceToSend;
import org.numo.dto.device.DevicesFromAPI;
import org.numo.dto.groupeDevice.DeviceGroupToSave;
import org.numo.error.TechnicalException;
import org.numo.functions.CalculateDeviceStatus;
import org.numo.functions.GetDevice;
import org.numo.functions.impl.GetDeviceFromApi;
import org.numo.service.CompanyService;
import org.numo.service.DeviceGroupService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceGroupServiceImpl implements DeviceGroupService {
    private final DeviceGroupRepository deviceGroupRepository;
    private final DeviceRepository deviceRepository;
    private final CompanyService companyService;
    @Override
    public List<DeviceGroup> deviceGroups() {
        return deviceGroupRepository.findAll();
    }

    @Override
    public DeviceGroup addDeviceGroup(DeviceGroupToSave deviceGroupToSave) {
        DeviceGroup deviceGroup = new DeviceGroup();
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        try {

            Company company = companyService.getCompanyForLoggedInUser(deviceGroupToSave.getEmail());
            if (company != null) {
                deviceGroup.setCompany(company);
                deviceGroup.setName(deviceGroupToSave.getName()+"-"+formattedTime+"-"+company.getName());
                DeviceGroup save = deviceGroupRepository.save(deviceGroup);
                return save;
            } else {
                return null;
            }


        } catch (TechnicalException e) {
            throw new TechnicalException("Error");
        }
    }
    @Override
    public DeviceGroup findDeviceGroupById(Long id) {
        return deviceGroupRepository.findById(id).orElse(null);
    }

    @Override
    public List<DeviceToSend> devicesFromGroup(Long id) {
        GetDevice getDevice = new GetDeviceFromApi();
        List<DevicesFromAPI> devicesFromAPI = getDevice.AllDevices(); // add time out
        Set<String> activeImies = new HashSet<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<DeviceToSend> listDevices = new ArrayList<>();

        for (DevicesFromAPI devices : devicesFromAPI) {
            List<Device> devicesByGroup = deviceGroupRepository.findById(id).orElse(null).getDevices();

            for (Device d : devicesByGroup) {
                Device deviceByImei = deviceRepository.findDeviceByImei(d.getImei());
                if (deviceByImei != null && devices.getIMEI().equals(d.getImei())) {
                    DeviceToSend device = new DeviceToSend();
                    device.setId(deviceByImei.getId());
                    device.setImei(devices.getIMEI());
                    device.setTime(devices.getLastSeen());
                    device.setFirmware(devices.getFirware());
                    device.setConfiguration(devices.getConfig());
                    if (deviceByImei.getCompany() != null) {
                        device.setCompany(deviceByImei.getCompany().getName());
                    }
                    if (deviceByImei.getClient() != null) {
                        device.setClient(deviceByImei.getClient().getName());
                    }
                    if (deviceByImei.getDeviceGroup() != null) {
                        device.setGroup(deviceByImei.getDeviceGroup().getName());
                    }
                    CalculateDeviceStatus status = new CalculateDeviceStatus();
                    device.setStatusDevice(status.calculateDeviceStatus(devices.getLastSeen(), currentDateTime));
                    activeImies.add(devices.getIMEI());
                    listDevices.add(device);
                }
            }
        }
        return listDevices;
    }

    @Override
    public void delete(Long id) {
        DeviceGroup deviceGroup = deviceGroupRepository.findById(id).orElse(null);
        List<Device> devices = deviceGroup.getDevices();
        if(devices.isEmpty()){
            for(Device d : devices){
                d.setDeviceGroup(null);
                deviceRepository.save(d);
            }
        }
        deviceGroupRepository.deleteById(id);
    }

    @Override
    public List<DeviceGroup> findDeviceGroupsWithoutDevices() {
        return deviceGroupRepository.findDeviceGroupsWithoutDevices();
    }

    @Override
    public Long count() {
        return deviceGroupRepository.count();
    }


}
