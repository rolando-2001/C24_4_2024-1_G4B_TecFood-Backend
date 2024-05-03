package com.backend.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="first_Name" )
    private String firstName;

    @Column(name ="last_name" )
    private String lastName;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="dni")
    private String dni;

    @Column(name="img_url")
    private String imgUrl;

    @Column(name="is_google_account")
    private boolean isGoogleAccount;
    @Column(name="is_verified_email")
    private boolean isVerifiedEmail;

    @Column(name = "role_id")
    private Integer roleId=2;





}
