package org.sid.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.sid.dto.client.ClientToSave;
import org.sid.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Api(tags = "client", description = "Endpoints to manage companies")
public class ClientController {
    private final ClientService clientService;

     @PostConstruct
    void init(){
         ClientToSave clientToSave = new ClientToSave("aya","bw11819","tessqbdbjsqbjbdbebbdzxnqddjsq",197219,"aya@gmail.com","29cabbfe-6aa3-4a6f-Ab0e-8e8a84326253");
         clientService.addClient(clientToSave);
    }

}
