package org.numo.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.Client;
import org.numo.dto.client.ClientToSave;
import org.numo.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Api(tags = "client", description = "Endpoints to manage clients")
public class ClientController {
    private final ClientService clientService;


    /* @PostConstruct
        void init(){
            ClientToSave clientToSave = new ClientToSave("marouane","bw11819","tessqbdbjsqbjbdbebbdzxnqddjsq",197219,"mr12@gmail.com","48fda0ce-136f-45ba-ba27-ddacc412521d");
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
}
