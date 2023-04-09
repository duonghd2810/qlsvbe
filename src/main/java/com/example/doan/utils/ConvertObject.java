package com.example.doan.utils;

import com.example.doan.dtos.MajorDTO;
import com.example.doan.dtos.UserDTO;
import com.example.doan.models.Major;
import com.example.doan.models.User;

public class ConvertObject {
    public static User convertUserDTOToUser(UserDTO userDTO, User user){
        if(userDTO.getFullname()!= null){
            user.setFullName(userDTO.getFullname());
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
}
