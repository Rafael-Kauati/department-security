package com.service.departmentsecurity.controller;

import com.service.departmentsecurity.entity.Client;
import com.service.departmentsecurity.event.RegistrationCompleteEvent;
import com.service.departmentsecurity.model.ClientModel;
import com.service.departmentsecurity.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/clients/")
//full path = localhost:8080/management/clients/**
public class ClientManagementController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ApplicationEventPublisher publisher;

    private static final List<Client> CLIENTS = Arrays.asList(
            new Client("Rafael", "Kauati", "rafaelkauati@ua.pt"),
            new Client("Daniel", "Madureira", "DaniMadu@ua.pt"),
            new Client("Liliana", "Ribeiro", "Lily@ua.pt")
    );


    @GetMapping("/all")
    public List<Client> getAllClients(){
        return CLIENTS;
    }

    @PostMapping("/register")
    public String registerClient(@RequestBody ClientModel clientModel, final HttpServletRequest request){
        Client client = clientService.registerClient(clientModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                client,
                applicationUrl(request)
        ));
        return " Client registration status : Success ";
    }

    @DeleteMapping(path = "/{fstname}")
    public void deleteClient(@PathVariable("fstname") String fstname){
        System.out.println(String.format("Client %s deleted sucessefuly ", fstname));
    }

    @PutMapping(path = "/{fstname}")
    public void updateClient(@PathVariable("fstname") String fstname, @RequestBody Client client){
        System.out.println(String.format("Client %s updated sucessefuly ", fstname));
    }

    public String applicationUrl(HttpServletRequest request){
        return "http://" +
                request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
