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
         ClientToSave clientToSave = new ClientToSave("marouane","bw17819","test",197219,"test","90c9b9a8-0981-48b7-b489-5a802274ef6f");
         clientService.addClient(clientToSave);
    }

}
