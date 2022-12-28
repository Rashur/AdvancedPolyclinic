package com.beresten.polyclinic.service.impl;

import com.beresten.polyclinic.dto.UserDto;
import com.beresten.polyclinic.exception.RoleNotFoundException;
import com.beresten.polyclinic.exception.UserAlreadyExistException;
import com.beresten.polyclinic.exception.UserNotFoundException;
import com.beresten.polyclinic.mapper.UserMapper;
import com.beresten.polyclinic.model.MedicalCard;
import com.beresten.polyclinic.model.Role;
import com.beresten.polyclinic.model.User;
import com.beresten.polyclinic.repository.MedicalCardRepository;
import com.beresten.polyclinic.repository.RoleRepository;
import com.beresten.polyclinic.repository.UserRepository;
import com.beresten.polyclinic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final MedicalCardRepository medicalCardRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveUser(UserDto userDto) {
        var existUser = userRepository
                .findUserByUsernameOrEmail(
                        userDto.getUsername(),
                        userDto.getEmail());
        if (existUser != null) {
            throw new UserAlreadyExistException("User with username: "
                    + userDto.getUsername() + " or email: "
                    + userDto.getEmail() + "is already exist");
        }
        log.info("IN UserServiceImpl saveUser() found user with username: {}, email: {}",
                userDto.getUsername(),
                userDto.getEmail());
        Role roleUser = roleRepository.findByName("ROLE_USER");
        Role rolePatient = roleRepository.findByName("ROLE_PATIENT");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);
        roles.add(rolePatient);
        var user = userMapper.userDtoToUser(userDto);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var medicalCard = new MedicalCard();
        medicalCard.setUser(user);
        medicalCardRepository.save(medicalCard);
        log.info("IN UserServiceImpl saveUser() save medical card with user id: {}", medicalCard.getUser().getId());
        var savedUser = userRepository.save(user);
        log.info("IN UserServiceImpl saveUser() save user with username: {}", user.getUsername());
        return savedUser;
    }

    @Override
    public User getUserByUsername(String username) {
        var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username: " + username + " not found");
        }
        return user;
    }

    @Override
    public UserDto getUserById(Integer id) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto update(Integer id, UserDto userDto) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
        userDto.setId(id);
        userRepository.save(userMapper.userDtoToUser(userDto));
        return userDto;
    }

    @Override
    public void delete(Integer id) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> expectedUsers = userRepository.findAll();
        if (expectedUsers.isEmpty()) {
            throw new IllegalStateException("List with users is empty!");
        }
        return expectedUsers
                .stream().
                map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addUserRole(Integer id, String roleName) {
        var role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new RoleNotFoundException("Role with name: " + roleName + " not found");
        }
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
        user.getRoles().add(role);
        userRepository.save(user);
    }

}
