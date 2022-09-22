package com.service.departmentsecurity.event.listener;

import com.service.departmentsecurity.event.RegistrationCompleteEvent;
import com.service.departmentsecurity.entity.Client;
import com.service.departmentsecurity.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private ClientService clientService;


    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //The verification token for the client(user) with link should be here :
        Client client = event.getClient();
        String token = UUID.randomUUID().toString();
        clientService.saveVerificationTokenForClient(token,client);
        //send Mail to user
        String url =
                event.getApplicationUrl() + "verifyRegistration?token=" + token ;

        //send verificationEmail()
        log.info("Click the link to verify your account : {} ", url);
    }
}
