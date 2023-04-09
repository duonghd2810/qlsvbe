package com.example.doan.services.imp;

import com.example.doan.dtos.UserDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.User;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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
        User newUser = this.modelMapper.map(userDTO,User.class);
        newUser.setId(user.get().getId());
        return userRepository.save(newUser);
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("User is not found");
        }
        userRepository.delete(user.get());
        return "Delete success";
    }
}
