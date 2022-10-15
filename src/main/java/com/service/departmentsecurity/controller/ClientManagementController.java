package com.service.departmentsecurity.controller;

import com.service.departmentsecurity.entity.Client;
import com.service.departmentsecurity.event.RegistrationCompleteEvent;
import com.service.departmentsecurity.model.ClientModel;
import com.service.departmentsecurity.repository.ClientRepository;
import com.service.departmentsecurity.service.ClientService;
import com.service.departmentsecurity.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/management/clients/")
//full path for the in local host = localhost:8080/management/clients/**
public class ClientManagementController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ClientRepository clientRepository;

    public ClientManagementController(ClientRepository clientRepository, ClientServiceImpl clientServiceImpl) {this.clientRepository = clientRepository; this.clientServiceImpl = clientServiceImpl;}

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE')")
    public List<Client> getAllClients(){return clientRepository.findAll();}


    @GetMapping(path = "/getById/{id}")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_EMPLOYEE')")
    public List<Client> getClientById(@PathVariable("id") Long id){
        return clientRepository.findAllById(Collections.singleton(id) );

    }

    @GetMapping(path = "getByFstName/{fstname}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE')")
    public Client getClientByFstName(@PathVariable("fstname") String fstname){
        return clientServiceImpl.findByFstName(fstname);

    }

    @PostMapping("/register")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    public String registerClient(@RequestBody ClientModel clientModel, final HttpServletRequest request){
        Client client = clientService.registerClient(clientModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                client,
                applicationUrl(request)
        ));
        return " Client registration status : Success ";
    }

    @DeleteMapping(path = "/{fstname}")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_EMPLOYEE')")
    public void deleteClient(@PathVariable("fstname") String fstname){
        clientServiceImpl.deleteByFstName(fstname);
    }

    @PutMapping(path = "/UpdateClient")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_EMPLOYEE')")
    public void updateClient(@RequestBody ClientModel c){
        Client client = clientService.registerClient(c);
        //System.out.println(String.format("Client %s updated sucessefuly ", fstname));
    }

    public String applicationUrl(HttpServletRequest request){
        return "http://" +
                request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
