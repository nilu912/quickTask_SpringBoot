package com.example.quicktask.common;

import lombok.Data;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor //it will create without argument constructor
//@AllArgsConstructor //it will create all defined arguments constructor
public class CommonResponseBean {
    private Boolean success;
    private Integer statusCode;
    private String errormsg;
    private Object data;

    public CommonResponseBean() {}
    public CommonResponseBean(Boolean success, Integer statusCode, String errormsg, Object data) {
        this.success = success;
        this.statusCode = statusCode;
        this.errormsg = errormsg;
        this.data = data;
    }

}
