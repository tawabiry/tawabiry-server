package com.que.que.User.AdminUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.que.que.User.User;
import com.que.que.User.UserRole;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@JsonSerialize(using = AdminUserSerializer.class)
public class AdminUser extends User {

    public AdminUser(
            String firstName,
            String lastName,
            LocalDateTime dateOfRegistration,
            LocalDate dateOfBirth,
            String countryOfOrigin,
            String password,
            String email,
            boolean locked,
            boolean enabled,
            String phoneCode,
            String phoneNumber,
            String location) {
        super(
                UserRole.TAWABIRY_SYSTEM_ADMIN, firstName, lastName, dateOfRegistration, dateOfBirth, countryOfOrigin,
                password,
                email,
                locked, enabled, phoneCode, phoneNumber, location);
    }

    public String getLastActive() {
        return "Just now";
    }
}
