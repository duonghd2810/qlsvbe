package com.example.doan.dtos;

import com.example.doan.enums.ResponseMessageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private Integer status;
    private ResponseMessageEnum message;
    private T result;
}
