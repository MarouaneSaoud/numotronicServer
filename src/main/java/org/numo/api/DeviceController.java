package org.numo.api;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.Device;

import org.numo.dto.company.DeviceToCompany;
import org.numo.dto.device.DeviceToSave;
import org.numo.dto.device.DeviceToSend;
import org.numo.service.DeviceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
@RequestMapping("/device")
@Api(tags = "Device", description = "Endpoints to manage devices")
@RequiredArgsConstructor
public class DeviceController extends AbstractController {
    private final DeviceService deviceService;
   /*@PostConstruct
    void init(){
        deviceService.addDevice(new DeviceToSave(3195,1841891299,"grs", 1L));
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
    public Device findDeviceByImei(@PathVariable int imei) {
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
    public boolean decommission(@PathVariable int imei){
        Boolean status = deviceService.decommissionDeviceToCompany(imei);
        return status;
    }
}
