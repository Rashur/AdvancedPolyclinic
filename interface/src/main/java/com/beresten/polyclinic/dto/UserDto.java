package com.beresten.polyclinic.dto;

import com.beresten.polyclinic.model.Entry;
import com.beresten.polyclinic.model.MedicalCard;
import com.beresten.polyclinic.model.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

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
    @Size(min = 5, max = 30)
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

    private String phoneNumber;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private List<Entry> entryList;

    private List<MedicalCard> medicalCardList;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", entryList=" + entryList.stream().map(entry -> entry.getId()) +
                ", medicalCardList=" + medicalCardList.stream().map(medicalCard -> medicalCard.getId()) +
                '}';
    }
}
