package com.service.departmentsecurity.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "Client")
@Table(name = "Clients")
public class Client {

    //Attributes/Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fstname", nullable = false)
    private String FstName;

    @Column(name = "lstname", nullable = false)
    private String LstName;

    @Column(name = "email", nullable = false)
    @NotNull
    private String email;

    @Column(length = 60, name = "password", nullable = false)
    @NotNull
    private String password;

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFstName() {
        return FstName;
    }

    public void setFstName(String fstName) {
        FstName = fstName;
    }

    public String getLstName() {
        return LstName;
    }

    public void setLstName(String lstName) {
        LstName = lstName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //constructors
    public Client() {}

    public Client(String fstName, String lstName, String email, String password) {
        FstName = fstName;
        LstName = lstName;
        this.email = email;
        this.password = password;
    }

    public Client(String fstName, String lstName, String email) {
        FstName = fstName;
        LstName = lstName;
        this.email = email;
    }
}
