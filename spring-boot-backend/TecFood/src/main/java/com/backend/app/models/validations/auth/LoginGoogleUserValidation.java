package com.backend.app.models.validations.auth;

import com.backend.app.models.dtos.auth.LoginGoogleUserDto;
import com.backend.app.utilities.ValidationsUtility;

public class LoginGoogleUserValidation {

    public static String validate(LoginGoogleUserDto loginGoogleUserDto) throws IllegalAccessException {
        System.out.println(loginGoogleUserDto + " LoginGoogleUserValidation");
        if(ValidationsUtility.hasNullField(loginGoogleUserDto)) return "One or more fields are empty";

        if (ValidationsUtility.isFieldEmpty(loginGoogleUserDto.email())) return "Email is required";
        if (!ValidationsUtility.isEmailValid(loginGoogleUserDto.email())) {
            return "Email must be a valid Tecsup email";
        }

        if (!ValidationsUtility.isNameValid(loginGoogleUserDto.firstName())) {
            return "First name must have at least 3 characters";
        }

        if (!ValidationsUtility.isNameValid(loginGoogleUserDto.lastName())) {
            return "Last name must have at least 3 characters";
        }

        if(
                !loginGoogleUserDto.isGoogleAccount() ||
                !loginGoogleUserDto.isEmailVerified()
        ){
            return "Email must be verified";
        }

        if(ValidationsUtility.isFieldEmpty(loginGoogleUserDto.imgUrl())) return "Image URL is required";
        if(!ValidationsUtility.isUrlValid(loginGoogleUserDto.imgUrl())) return "Image URL is not valid";

        return null;
    }


}
