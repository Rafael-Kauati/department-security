package com.service.departmentsecurity.service;

import com.service.departmentsecurity.entity.Client;
import com.service.departmentsecurity.model.ClientModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    public Client registerClient(ClientModel clientModel);

    public Client findByFstName(String fstname);

    void saveVerificationTokenForClient(String token, Client client);
}
