package com.example.doan.exceptions;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorResponse {
    private Integer status;
    private String message;
}
