package com.beresten.polyclinic.controller;

import com.beresten.polyclinic.dto.UserDto;
import com.beresten.polyclinic.model.User;
import com.beresten.polyclinic.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto update(@PathVariable Integer id,
                          @RequestBody UserDto userDto) {
        return userService.update(id, userDto);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

}
