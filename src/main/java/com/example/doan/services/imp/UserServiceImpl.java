package com.example.doan.services.imp;

import com.cloudinary.Cloudinary;
import com.example.doan.dtos.UserDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.Role;
import com.example.doan.models.User;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.IUserService;
import com.example.doan.utils.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Override
    public List<User> getAllTeacher() {
        return userRepository.getAllTeacher();
    }

    @Override
    public List<User> getAllStudent() {
        return userRepository.getAllStudent();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("User does not exists");
        }
        return user.get();
    }

    @Override
    public User updateUser(UserDTO userDTO, Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("User is not found");
        }
        User newUser = new User();
        ConvertObject.convertUserDTOToUser(userDTO,newUser);
        newUser.setId(user.get().getId());
        return userRepository.save(newUser);
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("User is not found");
        }
        for(Role role: user.get().getRoless()){
            userRepository.deleteRecordInRoleUser(id, role.getId());
        }
        userRepository.delete(user.get());
        return "Delete success";
    }

    @Override
    public String uploadAvatar(MultipartFile multipartFile,Long id) throws IOException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("User is not found");
        }
        String url = cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
        user.get().setAvatar(url);
        userRepository.save(user.get());
        return url;
    }
}
