package com.example.quicktask.common;

import lombok.Getter;

@Getter
public enum CommonEnum {

    USER("user"),
    ADMIN("admin");

    private final String value;

     CommonEnum(String value){
        this.value = value;
    }

//    public String getValue(){
//        return this.value;
//    }
}
