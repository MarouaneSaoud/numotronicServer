package org.numo.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.Client;
import org.numo.dao.entity.Device;
import org.numo.dto.client.ClientToSave;
import org.numo.dto.device.DeviceToSend;
import org.numo.service.ClientService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Api(tags = "client", description = "Endpoints to manage clients")
public class ClientController {
    private final ClientService clientService;

    /* @PostConstruct
        void init(){
            ClientToSave clientToSave = new ClientToSave("mauane","bw11819","tessqbdbjsqbjbdbebbdzxnqddjsq",197219,"mrdgh12@gmail.com","E3e9c845-Bdb5-45eb-9775-2a16a49e6703");
            clientService.addClient(clientToSave);
    }*/
    @GetMapping("/")
    public List<Client> clients(){
        List<Client> clients = clientService.clientList();
        return clients;
    }
    @PostMapping("/save")
    public Client addClient(@RequestBody ClientToSave clientToSave){

        Client client = clientService.addClient(clientToSave);
        return client;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        clientService.deleteClient(id);
    }
    @GetMapping("/count")
    public Long count(){
        return clientService.count();
    }
    @GetMapping("/findDevicesById/{id}")
    public List<Device> findDevicesById(@PathVariable String id){
        return clientService.findDevicesByClientId(id);
    }
    @GetMapping("/findDevicesByEmail/{email}")
    public List<DeviceToSend> findDevicesByClient(@PathVariable String email){
        Client client=clientService.getClientForLoggedInUser(email);
        return clientService.findDevicesByClient(client);
    }
    @GetMapping("/getClientForLoggedInUser/{email}")
    public Client getClientForLoggedInUser(@PathVariable String email){
         Client client=clientService.getClientForLoggedInUser(email);
         return client ;
    }
    @GetMapping("/countClientDevices/{email}")
    public Long countClientDevices (@PathVariable String email){
        Client client=clientService.getClientForLoggedInUser(email);
        Long count = clientService.countClientDevices(client);
        return count ;
    }

}
