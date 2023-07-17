package org.sid.api;

import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.Device;
import org.sid.dto.DeviceToSend;
import org.sid.dto.DevicesFromDTO;
import org.sid.model.StatusDevice;
import org.sid.service.DeviceService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
