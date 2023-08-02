package org.numo.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.DeviceGroup;

import org.numo.dto.groupeDevice.DeviceGroupToSave;
import org.numo.service.DeviceGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/deviceGroup")
@RequiredArgsConstructor
@Api(tags = "deviceGroup", description = "Endpoints to manage device group")
public class DeviceGroupController {
    private final DeviceGroupService deviceGroupService;
 /*  @PostConstruct
    void init() {
        DeviceGroupToSave deviceGroupToSave = new DeviceGroupToSave( "device groupe 1" ,"7ffc8cf9-7d33-4f7f-Bfc1-9180ec7045e2");
        deviceGroupService.addDeviceGroup(deviceGroupToSave);
    }*/
    @GetMapping("/")
    public List<DeviceGroup> getDeviceGroups() {
        List<DeviceGroup> deviceGroups = deviceGroupService.deviceGroups();
       return deviceGroups;
    }
    @PostMapping("/add")
    public DeviceGroup save(@RequestBody DeviceGroupToSave deviceGroupToSave){
        DeviceGroup deviceGroup = deviceGroupService.addDeviceGroup(deviceGroupToSave);
        return deviceGroup;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        deviceGroupService.delete(id);
    }
    @GetMapping("/findDeviceGroupsWithoutDevices")
    public List<DeviceGroup> findDeviceGroupsWithoutDevices(){
        List<DeviceGroup> deviceGroupsWithoutDevices = deviceGroupService.findDeviceGroupsWithoutDevices();
        return deviceGroupsWithoutDevices;
    }
    @GetMapping("/count")
    public Long count(){
        return deviceGroupService.count();
    }
}
