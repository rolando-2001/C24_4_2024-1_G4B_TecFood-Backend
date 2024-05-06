package com.backend.app.models.validations.auth;

import com.backend.app.models.dtos.auth.RegisterUserDto;
import com.backend.app.persistence.entities.UserEntity;
import com.backend.app.utilities.ValidationsUtility;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterUserValidation {

    public static String validate(RegisterUserDto registerUserDto) throws IllegalAccessException  {
        if(ValidationsUtility.hasNullField(registerUserDto)) return "One or more fields are empty";

        if (ValidationsUtility.isFieldEmpty(registerUserDto.email())) return "Email is required";
        if (!ValidationsUtility.isEmailValid(registerUserDto.email())) {
            return "Email must be a valid Tecsup email";
        }

        if (ValidationsUtility.isFieldEmpty(registerUserDto.password())) return "Password is required";
        if (!ValidationsUtility.isPasswordValid(registerUserDto.password())) {
            return "Password must have at least 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character";
        }

        if (!ValidationsUtility.isFieldEmpty(registerUserDto.phoneNumber())) {
            if (!ValidationsUtility.isPhoneNumberValid(registerUserDto.phoneNumber())) {
                return "Phone number must have 9 digits and be a valid phone number";
            }
        }

        if (!ValidationsUtility.isFieldEmpty(registerUserDto.dni())) {
            if (!ValidationsUtility.isDniValid(registerUserDto.dni())) {
                return "DNI must have 8 digits and be a valid DNI";
            }
        }

        if (!ValidationsUtility.isNameValid(registerUserDto.firstName())) {
            return "First name must have at least 3 characters";
        }

        if (!ValidationsUtility.isNameValid(registerUserDto.lastName())) {
            return "Last name must have at least 3 characters";
        }

        return  null;

    }
}
