package com.service.departmentsecurity.service;

import com.service.departmentsecurity.entity.Client;
import com.service.departmentsecurity.entity.VerificationToken;
import com.service.departmentsecurity.model.ClientModel;
import com.service.departmentsecurity.repository.ClientRepository;
import com.service.departmentsecurity.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    //@Autowired
    //private PasswordEncoder passwordEncoder;


    @Override
    public Client registerClient(ClientModel clientModel) {
        Client client = new Client();

        //client.setEmail(clientModel.getEmail()); client.setPassword( passwordEncoder.encode(clientModel.getPassword()) );

        client.setFstName(clientModel.getFstName()); client.setLstName(clientModel.getLstName());

        clientRepository.save(client);

        return client;
    }

    @Override
    public void saveVerificationTokenForClient(String token, Client client) {
        VerificationToken verificationToken
                = new VerificationToken(client,token);

        verificationTokenRepository.save(verificationToken);
    }
}
