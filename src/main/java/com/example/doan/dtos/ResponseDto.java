package com.example.doan.dtos;

import com.example.doan.enums.ResponseMessageEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private Integer status;
    private ResponseMessageEnum message;
    private T result;
}
