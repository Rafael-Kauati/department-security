package com.service.departmentsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {
    private Long id;
    private String FstName;
    private String LstName;
    private String email;
    private String password;
}
