package com.service.departmentsecurity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token; private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN"))
    private Client client;

    //Expiration time = 10 minutes

    private static final int EXPIRATION_TIME = 10;

    //To calculate the expiration time based on the current date && time
    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(new Date().getTime()); calendar.add(Calendar.MINUTE, expirationTime);

        return new Date(calendar.getTime().getTime());
    }

    // token constructor(full) if the client isn't registrated yet in the system
    public VerificationToken(Client client, String token){
        super();
        this.token = token; this.client = client; this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }

    // token constructor(whitout the Client entity) if the client is already registrated in the system
    public VerificationToken(String token) {
        super();
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }
}
