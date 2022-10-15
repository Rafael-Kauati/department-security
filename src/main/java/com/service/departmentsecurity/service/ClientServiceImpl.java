package com.service.departmentsecurity.service;

import com.service.departmentsecurity.entity.Client;
import com.service.departmentsecurity.entity.VerificationToken;
import com.service.departmentsecurity.model.ClientModel;
import com.service.departmentsecurity.repository.ClientRepository;
import com.service.departmentsecurity.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private  PasswordEncoder PasswordEncoder;


    @Override
    public Client registerClient(ClientModel clientModel) {
        Client client = new Client();

        //client.setEmail(clientModel.getEmail()); client.setPassword( passwordEncoder.encode(clientModel.getPassword()) );

        client.setFstName(clientModel.getFstName()); client.setLstName(clientModel.getLstName());

        client.setEmail(clientModel.getEmail()); client.setPassword( PasswordEncoder.encode(clientModel.getPassword()) );

        clientRepository.save(client);

        return client;
    }


    public List<Client> getAllClient(){return clientRepository.findAll();}

    public void deleteByFstName(String fstname){

        List<Client> CLIENTS = getAllClient();

        Client clientMatched = null;

        for(Client c : CLIENTS){
            if(c.getFstName().equals(fstname)){
                //clientMatched = c;
                clientRepository.delete(c);
            }
        }

    }

    public List<Client> findAllById(Long id) {
        return clientRepository.findAllById(Collections.singleton(id));
    }

    public Client findByFstName(String fstname){
        final List<Client> CLIENTS = clientRepository.findAll();

        Client clientMatched = null;

        for(Client c :  CLIENTS){
            if(c.getFstName().equals(fstname)){
                clientMatched = c;
            }
        }

        return clientMatched;

    }

    @Override
    public void saveVerificationTokenForClient(String token, Client client) {
        VerificationToken verificationToken
                = new VerificationToken(client,token);

        verificationTokenRepository.save(verificationToken);
    }
}
