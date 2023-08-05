package org.numo.service.impl;

import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.Company;
import org.numo.dao.entity.DeviceGroup;
import org.numo.dao.repository.DeviceGroupRepository;
import org.numo.dto.groupeDevice.DeviceGroupToSave;
import org.numo.error.TechnicalException;
import org.numo.service.CompanyService;
import org.numo.service.DeviceGroupService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceGroupServiceImpl implements DeviceGroupService {
    private final DeviceGroupRepository deviceGroupRepository;
    private final CompanyService companyService;
    @Override
    public List<DeviceGroup> deviceGroups() {
        return deviceGroupRepository.findAll();
    }

    @Override
    public DeviceGroup addDeviceGroup(DeviceGroupToSave deviceGroupToSave) {
        DeviceGroup deviceGroup = new DeviceGroup();
        try {

            Company company = companyService.getCompanyById(deviceGroupToSave.getCompany());
            if (company != null) {
                deviceGroup.setCompany(company);
                deviceGroup.setName(deviceGroupToSave.getName()+"-"+company.getName());
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
        return null;
    }
    @Override
    public void delete(Long id) {
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
