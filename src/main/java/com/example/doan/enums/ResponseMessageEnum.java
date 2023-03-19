package com.example.doan.enums;

public enum ResponseMessageEnum {
    SUCCESS("Success"),
    ERROR("Error");
    private String message;

    ResponseMessageEnum(String message) {
        this.message = message;
    }
}
