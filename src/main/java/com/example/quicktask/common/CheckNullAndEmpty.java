package com.example.quicktask.common;

//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor
//@AllArgsConstructor
public class CheckNullAndEmpty {
//    private Object value;
//    public CheckNullAndEmpty(Object obj) {
//        this.value = obj;
//    }

    public static Boolean isNull(Object obj) {
        return obj == null;
    }
    public static Boolean isEmpty(Object obj) {
        return  obj.toString().isEmpty();
//        if(obj.getClass().getName().equals("String"))
//            return obj.toString().isEmpty();
//        else if (obj.getClass().getName().equals("Integer"))
//            return obj.toString().isEmpty();
//
//        return false;
    }
    public static Boolean isNullAndEmpty(Object obj) {
        return obj == null || obj.toString().isEmpty();
    }
}
