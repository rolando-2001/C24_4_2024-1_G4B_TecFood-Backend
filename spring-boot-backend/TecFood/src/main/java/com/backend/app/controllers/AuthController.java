package com.backend.app.controllers;

import com.backend.app.models.IAuthService;
import com.backend.app.models.dtos.auth.LoginUserDto;
import com.backend.app.models.dtos.auth.RegisterUserDto;
import com.backend.app.models.responses.auth.LoginUserResponse;
import com.backend.app.models.responses.auth.RegisterUserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody @Valid RegisterUserDto registerUserDto) throws Exception {
        return new ResponseEntity<>(authService.register(registerUserDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody @Valid LoginUserDto loginUserDto) throws Exception {
        System.out.println(loginUserDto + "AuthRequest");
        return new ResponseEntity<>(authService.login(loginUserDto), HttpStatus.OK);
    }

    @PostMapping("/login-google")
    public ResponseEntity<LoginUserResponse> loginGoogle(@RequestBody @Valid LoginUserDto loginUserDto) throws Exception {
        System.out.println(loginUserDto + "AuthRequest");
        return new ResponseEntity<>(authService.login(loginUserDto), HttpStatus.OK);
    }


}