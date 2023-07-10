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
        String apiUrl = "https://api.thingspeak.com/channels/1053969/fields/1/last?api_key=TW9N2WQSI0AGG912";         Object[] objects= restTemplate.getForObject(apiUrl,Object[].class);
       return Arrays.asList(objects);
    }
}
