package com.beresten.polyclinic.jwt;

import com.beresten.polyclinic.model.Role;
import com.beresten.polyclinic.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                toGrantedAuthority(user.getRoles())
        );
    }

    private static List<GrantedAuthority> toGrantedAuthority(List<Role> roles) {
        List<GrantedAuthority> userAuthority = new ArrayList<>();
        roles.stream()
                .map(role -> userAuthority
                        .add(new SimpleGrantedAuthority(role.toString())));
        return userAuthority;
    }

}
