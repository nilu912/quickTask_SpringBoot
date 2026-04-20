package com.example.quicktask.services;

import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.LoginUserDTO;
import com.example.quicktask.dtos.RegisterUserDTO;

public interface AuthService {

    CommonResponseBean registerUser(RegisterUserDTO DTO);
    CommonResponseBean loginUser(LoginUserDTO DTO);

}
