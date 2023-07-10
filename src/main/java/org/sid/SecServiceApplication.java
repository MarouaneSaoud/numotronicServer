package org.sid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SecServiceApplication {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(SecServiceApplication.class, args);
    }

    /*CommandLineRunner start(AccountService accountService){
        return args->{
            accountService.saveRole(new AppRole(null,"USER"));
            accountService.saveRole(new AppRole(null,"ADMIN"));
            Stream.of("user1","user2","user3","admin").forEach(un->{
                accountService.saveUser(un,"1234","1234");
            });
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("user1","USER");

            RestTemplate restTemplate = new RestTemplate();
            String apiUrl="https://229e-41-141-238-134.ngrok-free.app/devices";
            ResponseEntity<LinkedHashMap[]> responseEntity = restTemplate.getForEntity(apiUrl, LinkedHashMap[].class);
            LinkedHashMap[] usersArray = responseEntity.getBody();

            List<DevicesFromApi> deviceList = new ArrayList<>();

            for (LinkedHashMap deviceMap : usersArray) {
                DevicesFromApi devices = new DevicesFromApi();
                devices.setImei((String) deviceMap.get("imei"));
                devices.setConfig((String) deviceMap.get("config"));
                devices.setReference((String) deviceMap.get("reference"));
                devices.setLastSeen((String) deviceMap.get("lastSeen"));
                deviceList.add(devices);
            }
            System.out.println(deviceList);
        };

    }*/
    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }

}

