package com.beresten.polyclinic.service;

import com.beresten.polyclinic.dto.UserDto;
import com.beresten.polyclinic.model.Role;
import com.beresten.polyclinic.model.User;

import java.util.List;

public interface UserService {

    User saveUser(UserDto userDto);

    User getUserByUsername(String username);

    UserDto getUserById(Integer id);

    UserDto update(Integer id, UserDto userDto);

    void delete(Integer id);

    List<UserDto> getAllUsers();

    void addUserRole(Integer id, String roleName);

}
