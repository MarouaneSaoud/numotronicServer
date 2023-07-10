package org.sid.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping
public class DeviceController {

    private RestTemplate restTemplate;
    @GetMapping("/device")
    public List<Object>getObjects(){
        String apiUrl = "https://0da9-196-70-5-3.ngrok-free.app/devices";
        Object[] objects= restTemplate.getForObject(apiUrl,Object[].class);
       return Arrays.asList(objects);
    }
}
