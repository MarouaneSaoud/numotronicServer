package org.sid.api;

import lombok.RequiredArgsConstructor;
import org.sid.service.DeviceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class DeviceController extends AbstractController {

    private final RestTemplate restTemplate;


    @GetMapping("/device")
    public List<Object> getObjects() {
        String apiUrl = "https://0da9-196-70-5-3.ngrok-free.app/devices";
        Object[] objects = restTemplate.getForObject(apiUrl, Object[].class);
        return Arrays.asList(objects);
    }
}
