package com.backend.app.services;

import com.backend.app.exception.CustomException;
import com.backend.app.models.IAuthService;
import com.backend.app.models.dtos.auth.LoginGoogleUserDto;
import com.backend.app.models.dtos.auth.LoginUserDto;
import com.backend.app.models.dtos.auth.RegisterUserDto;
import com.backend.app.models.responses.auth.LoginUserResponse;
import com.backend.app.models.responses.auth.RegisterUserResponse;
import com.backend.app.models.validations.auth.LoginGoogleUserValidation;
import com.backend.app.models.validations.auth.RegisterUserValidation;
import com.backend.app.persistence.entities.ERole;
import com.backend.app.persistence.entities.UserEntity;
import com.backend.app.persistence.repositories.RoleRepository;
import com.backend.app.persistence.repositories.UserRepository;
import com.backend.app.utilities.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtility jwtUtility;

    @Override
    public LoginUserResponse login(LoginUserDto loginUserDto) throws Exception {
        UserEntity user = userRepository.findByEmail(loginUserDto.email());
        if (user == null) throw CustomException.badRequest("Email or password is incorrect");
        if(!passwordEncoder.matches(loginUserDto.password(), user.getPassword())) {
            throw CustomException.badRequest("Email or password is incorrect");
        }
        String token = jwtUtility.generateJWT(user.getId());
        return new LoginUserResponse(
                "Login successful",
                user,
                token
        );
    }

    public LoginUserResponse loginGoogle(LoginGoogleUserDto loginGoogleUserDto) throws Exception {
        String error = LoginGoogleUserValidation.validate(loginGoogleUserDto);
        if(error != null) throw CustomException.badRequest(error);

        UserEntity user = userRepository.findByEmail(loginGoogleUserDto.email());
        if (user == null) {
            user = UserEntity.builder()
                    .email(loginGoogleUserDto.email())
                    .password(passwordEncoder.encode("google" + loginGoogleUserDto.email()))
                    .role(roleRepository.findByName(ERole.ROLE_USER))
                    .firstName(loginGoogleUserDto.firstName())
                    .lastName(loginGoogleUserDto.lastName())
                    .imgUrl(loginGoogleUserDto.imgUrl())
                    .isGoogleAccount(true)
                    .isVerifiedEmail(true)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            userRepository.save(user);
        } else {
            if (!user.isGoogleAccount() || !user.isVerifiedEmail()) {
                user.setGoogleAccount(true);
                user.setVerifiedEmail(true);
                user.setUpdatedAt(LocalDateTime.now());
                userRepository.save(user);
            }
        }

        String token = jwtUtility.generateJWT(user.getId());
        return new LoginUserResponse(
                "Login successful",
                user,
                token
        );
    }

    public RegisterUserResponse register(RegisterUserDto registerUserDto) throws Exception {
        UserEntity user = userRepository.findByEmail(registerUserDto.email());
        if (user != null) throw CustomException.badRequest("Email already exists");

        String error = RegisterUserValidation.validate(registerUserDto);
        if(error != null) throw CustomException.badRequest(error);

        user = UserEntity.builder()
                .email(registerUserDto.email())
                .password(passwordEncoder.encode(registerUserDto.password()))
                .role(roleRepository.findByName(ERole.ROLE_USER))
                .firstName(registerUserDto.firstName())
                .lastName(registerUserDto.lastName())
                .phoneNumber(registerUserDto.phoneNumber())
                .dni(registerUserDto.dni())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return new RegisterUserResponse(
                "User " + user.getEmail() + " created successfully"
        );
    }

}
