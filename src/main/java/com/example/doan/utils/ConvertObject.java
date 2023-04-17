package com.example.doan.utils;

import com.example.doan.dtos.CourseGradeDTO;
import com.example.doan.dtos.MajorDTO;
import com.example.doan.dtos.UserDTO;
import com.example.doan.models.CourseGrade;
import com.example.doan.models.Major;
import com.example.doan.models.User;

public class ConvertObject {
    public static User convertUserDTOToUser(UserDTO userDTO, User user){
        if(userDTO.getFullName()!= null){
            user.setFullName(userDTO.getFullName());
        }
        if(userDTO.getDateOfBirth() != null){
            user.setDateOfBirth(userDTO.getDateOfBirth());
        }
        if(userDTO.getAddress() != null){
            user.setAddress(userDTO.getAddress());
        }
        if(userDTO.getEmail() != null){
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getAvatar() != null){
            user.setAvatar(userDTO.getAvatar());
        }
        if(userDTO.getGender() != null){
            user.setGender(userDTO.getGender());
        }
        if(userDTO.getPhone() != null){
            user.setPhone(userDTO.getPhone());
        }
        return user;
    }

    public static Major convertMajorDTOToMajor(MajorDTO majorDTO,Major major){
        if(majorDTO.getMajorName() != null){
            major.setMajorName(majorDTO.getMajorName());
        }
        if(majorDTO.getDeanName() != null){
            major.setDeanName(majorDTO.getDeanName());
        }
        return major;
    }
    public static CourseGrade convertCourseGradeDTOToCourseGrade(CourseGradeDTO courseGradeDTO,CourseGrade courseGrade){
        if(courseGradeDTO.getHs1() != null){
            courseGrade.setHs1(courseGradeDTO.getHs1());
        }
        if(courseGradeDTO.getHs2() != null){
            courseGrade.setHs2(courseGradeDTO.getHs2());
        }
        if(courseGradeDTO.getFinaltest() != null){
            courseGrade.setFinaltest(courseGradeDTO.getFinaltest());
        }
        return courseGrade;
    }
}
