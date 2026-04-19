package com.example.quicktask.controllers;

import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.RegisterUserDTO;
import com.example.quicktask.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    public AuthService authService;

    @PostMapping("/register")
    public CommonResponseBean registerUser(@RequestBody RegisterUserDTO DTO) {
        return authService.registerUser(DTO);
    }
    @PostMapping("/login")
    public CommonResponseBean loginUser(@RequestBody RegisterUserDTO DTO) {
        return authService.loginUser(DTO);
    }
}
