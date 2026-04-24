package com.example.quicktask.dtos;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String name, password, email, address, role;
    private Integer age;
}
