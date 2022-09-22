package com.service.departmentsecurity.event;

import com.service.departmentsecurity.entity.Client;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private Client client; private String applicationUrl;

    public RegistrationCompleteEvent(Client client, String applicationUrl) {
        super(client);
        this.client = client;
        this.applicationUrl = applicationUrl;
    }
}

