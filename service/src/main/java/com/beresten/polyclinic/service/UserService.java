package com.beresten.polyclinic.service;

import com.beresten.polyclinic.dto.UserDto;
import com.beresten.polyclinic.model.Role;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto getUser(String username);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    void changeUserWorkRole(Integer id, String workRole);

    void saveRole(Role role);
}
