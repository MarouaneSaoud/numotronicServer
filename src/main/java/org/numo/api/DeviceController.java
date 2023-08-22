package org.numo.api;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.Device;

import org.numo.dto.client.DeviceToClient;
import org.numo.dto.company.DeviceToCompany;
import org.numo.dto.device.DeviceToSave;
import org.numo.dto.device.DeviceToSend;
import org.numo.dto.groupeDevice.DeviceToGroup;
import org.numo.service.DeviceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping("/device")
@Api(tags = "Device", description = "Endpoints to manage devices")
@RequiredArgsConstructor
public class DeviceController extends AbstractController {
    private final DeviceService deviceService;

   /*@PostConstruct
    void init(){
        deviceService.addDevice(new DeviceToSave(3195,3195131219,"grs", 1L));
    }*/
    @GetMapping("/")
    public List<DeviceToSend> getDevice() {
        List<DeviceToSend> devicelist = deviceService.devicelist();
        return devicelist;
    }
    @PostMapping("/add")
    public  Device save(@RequestBody DeviceToSave device){
        Device d = deviceService.addDevice(device);
        return d;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        deviceService.delete(id);
    }

    @GetMapping("/{imei}")
    public Device findDeviceByImei(@PathVariable String imei) {
        return deviceService.findDeviceByImei(imei);
    }
    @GetMapping("/count")
    public Long countDevices (){
       return deviceService.countDevices();
    }
    @GetMapping("/DevicesWithoutCompany")
    public List<Device> findDevicesWithoutCompany (){
        List<Device> devicesWithoutCompany = deviceService.findDevicesWithoutCompany();
        return devicesWithoutCompany;
    }
    @PostMapping("/allocateDevice")
    public  boolean allocate(@RequestBody DeviceToCompany device){
        Boolean status = deviceService.allocateDeviceToCompany(device);
        return status;
    }
    @GetMapping("/decommissionDevice/{imei}")
    public boolean decommission(@PathVariable String imei){
        Boolean status = deviceService.decommissionDeviceToCompany(imei);
        return status;
    }
    @PostMapping("/allocateDeviceToClient")
    public  boolean allocateDeviceToClient(@RequestBody DeviceToClient deviceToClient){
        Boolean status = deviceService.allocateDeviceToClient(deviceToClient);
        return status;
    }
    @GetMapping("/decommissionDeviceToClient/{imei}")
    public boolean decommissionDeviceToClient(@PathVariable String imei){
        Boolean status = deviceService.decommissionDeviceToClient(imei);
        return status;
    }
    @PostMapping("/allocateDeviceToGroup")
    public boolean allocateDeviceToGroup(@RequestBody DeviceToGroup deviceToGroup){
        return deviceService.allocateDeviceToGroup(deviceToGroup);
    }
    @GetMapping("/removeDeviceFromGroup/{imei}")
    public boolean removeDeviceFromGroup(@PathVariable String imei){
        Boolean status = deviceService.removeDeviceFromGroup(imei);
        return status;
    }
}