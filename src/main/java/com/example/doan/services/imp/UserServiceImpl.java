package com.example.doan.services.imp;

import com.example.doan.dtos.UserDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.Role;
import com.example.doan.models.User;
import com.example.doan.repositories.RoleRepository;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.IUserService;
import com.example.doan.utils.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> getAllTeacher() {
        return null;
    }

    @Override
    public List<User> getAllStudent() {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user == null){
            throw new NotFoundException("User does not exists");
        }
        return user.get();
    }

    @Override
    public User createTeacher(UserDTO userDTO) {
        User user = new User();
        ConvertObject.convertUserDTOToUser(userDTO,user);
        Set<Role> roles = new HashSet<>(roleRepository.findAll());
        Set<Role> roleResult  = new HashSet<>();
        for(Role role: roles){
            if("teacher".equals(role.getRoleName())){
                roleResult.add(role);
            }
        }
        user.setRoless(roleResult);
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public User createStudent(UserDTO userDTO) {
        User user = new User();
        ConvertObject.convertUserDTOToUser(userDTO,user);
        Set<Role> roles = new HashSet<>(roleRepository.findAll());
        Set<Role> roleResult  = new HashSet<>();
        for(Role role: roles){
            if("student".equals(role.getRoleName())){
                roleResult.add(role);
            }
        }
        user.setRoless(roleResult);
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public User updateUser(UserDTO userDTO, Long id) {
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        return null;
    }
}
