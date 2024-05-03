package com.backend.app.dto;


import java.io.Serializable;

import lombok.*;

@Data
@ToString
@Builder
public class UserDto implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String dni;
    private String imgUrl;
    private boolean isGoogleAccount;
    private boolean isVerifiedEmail;
    private Integer roleId=2;

}
