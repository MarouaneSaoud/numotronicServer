package org.sid;

import org.sid.dto.DevicesFromDTO;
import org.sid.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class SecServiceApplication {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(SecServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(AccountService accountService) {
        return args -> {
            /*accountService.saveRole(new AppRole(null, "USER"));
            accountService.saveRole(new AppRole(null, "ADMIN"));
            Stream.of("user1", "user2", "user3", "admin").forEach(un -> {
                accountService.saveUser(un, "1234", "1234");
            });
            accountService.addRoleToUser("admin", "ADMIN");
            accountService.addRoleToUser("user1", "USER");
*/
           /* RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "https://api.thingspeak.com/channels/1053969/fields/1/last?api_key=TW9N2WQSI0AGG912";
            ResponseEntity<LinkedHashMap[]> responseEntity = restTemplate.getForEntity(apiUrl, LinkedHashMap[].class);
            LinkedHashMap[] usersArray = responseEntity.getBody();

            List<DevicesFromDTO> deviceList = new ArrayList<>();

            for (LinkedHashMap deviceMap : usersArray) {
                DevicesFromDTO devices = new DevicesFromDTO();
                devices.setImei((String) deviceMap.get("imei"));
                devices.setConfig((String) deviceMap.get("reference"));
                devices.setReference((String) deviceMap.get("config"));
                devices.setLastSeen((String) deviceMap.get("lastSeen"));
                deviceList.add(devices);
            }
            System.out.println(deviceList);*/
        };

    }
    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }

}

