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
                new ResponseDto<T>(HttpStatus.OK.value(), ResponseMessageEnum.SUCCESS,data)
        );
    }
    public ResponseEntity<?> resListSuccess(List<T> list){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(),ResponseMessageEnum.SUCCESS,list)
        );
    }
    public ResponseEntity<?> resSetSuccess(Set<T> set){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(HttpStatus.OK.value(),ResponseMessageEnum.SUCCESS,set)
        );
    }
}
