package com.example.doan.bases;

import com.example.doan.dtos.ResponseDto;
import com.example.doan.enums.ResponseMessageEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public class BaseController<T> {
    public ResponseEntity<?> resSuccess(T data){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<T>(ResponseMessageEnum.SUCCESS,data)
        );
    }
    public ResponseEntity<?> resListSuccess(List<T> list){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(ResponseMessageEnum.SUCCESS,list)
        );
    }
    public ResponseEntity<?> resSetSuccess(Set<T> set){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(ResponseMessageEnum.SUCCESS,set)
        );
    }
    public ResponseEntity<?> resStringSuccess(String message){
        return ResponseEntity.status(HttpStatus.OK).body(
          new ResponseDto<>(ResponseMessageEnum.SUCCESS, message)
        );
    }
}
