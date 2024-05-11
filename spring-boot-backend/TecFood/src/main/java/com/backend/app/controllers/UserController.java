package com.backend.app.controllers;

import com.backend.app.models.IUserService;
import com.backend.app.models.dtos.auth.RegisterUserDto;
import com.backend.app.models.responses.auth.RegisterUserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
}

/*
@PostMapping("/register")
public ResponseEntity<RegisterUserResponse> register(@RequestBody @Valid RegisterUserDto registerUserDto) throws Exception {
    return new ResponseEntity<>(authService.register(registerUserDto), HttpStatus.OK);
}

 */