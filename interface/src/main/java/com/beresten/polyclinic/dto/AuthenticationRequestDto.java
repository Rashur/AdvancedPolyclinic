package com.beresten.polyclinic.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    private String username;
    private String password;
}
