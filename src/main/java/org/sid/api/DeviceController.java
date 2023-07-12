package org.sid.api;

import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.Device;
import org.sid.dto.DevicesFromDTO;
import org.sid.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;


@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
public class DeviceController extends AbstractController {

    private final RestTemplate restTemplate;
    private final DeviceService deviceService;

    @GetMapping("/")
    public List<Device> getDevices() {
       return null;
    }

    @PostMapping("/add")
    public Device save(Device device) {
        Device d = deviceService.AddDevice(device);
        return d;
    }
    @GetMapping("/deleteDevice")
    public void delete(Long id) {
        deviceService.delete(id);
    }

}
