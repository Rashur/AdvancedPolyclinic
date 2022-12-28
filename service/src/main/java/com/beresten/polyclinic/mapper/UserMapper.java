package com.beresten.polyclinic.mapper;

import com.beresten.polyclinic.dto.UserDto;
import com.beresten.polyclinic.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "phoneNumber", source = "phoneNumber"),
            @Mapping(target = "dateOfBirth", source = "dateOfBirth"),
            @Mapping(target = "entryList", source = "entryList"),
            @Mapping(target = "medicalCardList", source = "medicalCardList")
    })
    UserDto userToUserDto(User user);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "phoneNumber", source = "phoneNumber"),
            @Mapping(target = "dateOfBirth", source = "dateOfBirth"),
            @Mapping(target = "entryList", source = "entryList"),
            @Mapping(target = "medicalCardList", source = "medicalCardList")
    })
    User userDtoToUser(UserDto userDto);
}
