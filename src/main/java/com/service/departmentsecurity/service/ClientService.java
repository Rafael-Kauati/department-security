package com.service.departmentsecurity.service;

import com.service.departmentsecurity.entity.Client;
import com.service.departmentsecurity.model.ClientModel;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    public Client registerClient(ClientModel clientModel);
    void saveVerificationTokenForClient(String token, Client client);
}
