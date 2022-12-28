package com.beresten.polyclinic.controller;

import com.beresten.polyclinic.dto.AuthenticationRequestDto;
import com.beresten.polyclinic.dto.AuthenticationResponseDto;
import com.beresten.polyclinic.dto.UserDto;
import com.beresten.polyclinic.jwt.JwtTokenProvider;
import com.beresten.polyclinic.model.User;
import com.beresten.polyclinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDto login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        try {
            String username = authenticationRequestDto.getUsername();
            authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    authenticationRequestDto.getPassword()));
            User user = userService.getUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(user);
            AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
            authenticationResponseDto.setUsername(username);
            authenticationResponseDto.setToken(token);
            return authenticationResponseDto;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
