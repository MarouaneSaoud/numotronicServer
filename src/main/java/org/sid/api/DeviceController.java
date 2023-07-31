package org.sid.api;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.Device;

import org.sid.dto.device.DeviceDto;
import org.sid.dto.device.DeviceToSave;
import org.sid.dto.device.DeviceToSend;
import org.sid.service.DeviceService;
import org.sid.service.ReferenceService;
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
  /*  @PostConstruct
    void init(){
        deviceService.addDevice(new DeviceToSave(319528549,319528549,"grs", 1L));
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
    public DeviceDto findDeviceByImie(@PathVariable int imei){
        return deviceService.findDeviceByImei(319528549);
    }

    @GetMapping("/count")
    public Long countDevices (){
       return deviceService.countDevices();
    }
}
