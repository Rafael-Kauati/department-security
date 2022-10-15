package com.service.departmentsecurity.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientModel {
    private Long id;
    private String FstName;
    private String LstName;
    private String email;
    private String password;
}
