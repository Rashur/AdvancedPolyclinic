package com.beresten.polyclinic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 30)
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 30)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 30)
    private String lastName;

    @NotNull
    @NotBlank
    @Email
    private String email;




}
