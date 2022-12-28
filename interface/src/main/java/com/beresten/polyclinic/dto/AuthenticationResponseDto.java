package com.beresten.polyclinic.dto;

import lombok.Data;

@Data
public class AuthenticationResponseDto {

    private String username;
    private String token;
}
