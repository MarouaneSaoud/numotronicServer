package org.sid.api;
import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.Device;

import org.sid.dto.device.DeviceToSave;
import org.sid.dto.device.DeviceToSend;
import org.sid.service.DeviceService;
import org.sid.service.ReferenceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
public class DeviceController extends AbstractController {
    private final DeviceService deviceService;
    private final ReferenceService referenceService;




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

    @GetMapping("/{imie}")
    public Device findDeviceByImie(@PathVariable Integer imei){
        return deviceService.findDeviceByImei(imei);
    }

    @GetMapping("/count")
    public Long countDevices (){
       return deviceService.countDevices();
    }
}
