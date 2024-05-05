package com.backend.app.models.validations.auth;

import com.backend.app.models.dtos.auth.RegisterUserDto;
import com.backend.app.persistence.entities.UserEntity;

public class RegisterUserValidation {
    public static String validate(RegisterUserDto registerUserDto) {
        if (registerUserDto.email() == null || registerUserDto.email().isEmpty()) return "Email is required";
        if (!registerUserDto.email().matches("^[A-Za-z0-9+_.-]+@tecsup\\.edu\\.pe$")) return "Email must be a valid Tecsup email";

        if (registerUserDto.password() == null || registerUserDto.password().isEmpty()) return "Password is required";
        if (!registerUserDto.password().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            return "Password must contain at least one digit, one uppercase letter, one lowercase letter, and at least 8 characters";
        }

        System.out.println(registerUserDto.phoneNumber() + " phone" );

        if(!registerUserDto.phoneNumber().isEmpty()) {
            if (!registerUserDto.phoneNumber().matches("^\\d{9}$")) return "Phone number must have 9 digits and be a valid phone number";
        }

        if(!registerUserDto.dni().isEmpty()) {
            if (!registerUserDto.dni().matches("^\\d{8}$")) return "DNI must have 8 digits and be a valid DNI";
        }

        if (registerUserDto.firstName() == null || registerUserDto.firstName().isEmpty() || registerUserDto.firstName().length() < 3) {
            return "First name is required and must have at least 3 characters";
        }
        if (registerUserDto.lastName() == null || registerUserDto.lastName().isEmpty() || registerUserDto.lastName().length() < 3) {
            return "Last name is required and must have at least 3 characters";
        }

        return  null;

    }
}
