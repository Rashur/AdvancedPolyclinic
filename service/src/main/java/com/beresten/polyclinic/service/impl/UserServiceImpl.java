package com.beresten.polyclinic.service.impl;

import com.beresten.polyclinic.dto.UserDto;
import com.beresten.polyclinic.exception.UserAlreadyExistException;
import com.beresten.polyclinic.mapper.UserMapper;
import com.beresten.polyclinic.model.MedicalCard;
import com.beresten.polyclinic.model.Role;
import com.beresten.polyclinic.model.User;
import com.beresten.polyclinic.model.WorkRole;
import com.beresten.polyclinic.repository.MedicalCardRepository;
import com.beresten.polyclinic.repository.UserRepository;
import com.beresten.polyclinic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final MedicalCardRepository medicalCardRepository;

    @Override
    public UserDto saveUser(UserDto userDto) {
        var existUser = userRepository
                .findUserByUsernameOrEmail(
                        userDto.getUsername(),
                        userDto.getEmail());
        if (existUser != null) {
            throw new UserAlreadyExistException("User with username: "
                    + userDto.getUsername() + " or email: "
                    + userDto.getEmail() + "is already exist");
        }
        var user = userMapper.userDtoToUser(userDto);
        user.setRole(Role.ROLE_USER);
        user.setWorkRole(WorkRole.PATIENT);
        var medicalCard = new MedicalCard();
        medicalCard.setUser(user);
        userRepository.save(user);
        medicalCardRepository.save(medicalCard);
        return userDto;
    }

    @Override
    public UserDto getUser(String username) {
        return null;
    }

    @Override
    public UserDto getUserById(Integer id) {
        return null;
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
    public void changeUserWorkRole(Integer id, String workRole) {

    }
}
