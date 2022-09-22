package com.service.departmentsecurity.controller;


import com.service.departmentsecurity.entity.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management")
public class ClientController {
    private static final List<Client> CLIENTS = Arrays.asList(
            new Client("Rafael", "Kauati", "rafaelkauati@ua.pt"),
            new Client("Daniel", "Madureira", "DaniMadu@ua.pt"),
            new Client("Liliana", "Ribeiro", "Lily@ua.pt")
    );
        //full path = localhost:8080/management/clients/{fstname}
    @GetMapping(path = "clients/{fstname}")
    public Client getClientByFstName(@PathVariable("fstname") String fstname){
        return CLIENTS
                .stream()
                .filter(client -> fstname.equals(client.getFstName()))
                .findFirst().orElseThrow( () -> new IllegalStateException("Client with such" +
                        " a name does not exist : " + fstname));
    }
}

