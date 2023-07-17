package org.sid.api;
import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.Device;
import org.sid.dto.DeviceToSend;
import org.sid.service.DeviceService;
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
    @GetMapping("/")
    public List<DeviceToSend> getdevice() {
        List<DeviceToSend> devicelist = deviceService.devicelist();
        return devicelist;
    }
    @PostMapping("/add")
    public  Device save(@RequestBody Device device){
        Device d = deviceService.addDevice(device);

        return d;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        deviceService.delete(id);
    }

}
