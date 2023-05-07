package com.example.doan.services.imp;

import com.example.doan.dtos.PasswordDTO;
import com.example.doan.dtos.UserDTO;
import com.example.doan.dtos.UsernameDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.Role;
import com.example.doan.models.User;
import com.example.doan.repositories.UserRepository;
import com.example.doan.services.IMailService;
import com.example.doan.services.IUserService;
import com.example.doan.utils.ConvertObject;
import com.example.doan.utils.GenaralDataUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IMailService mailService;
//    @Autowired
//    private Cloudinary cloudinary;

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
    public List<User> getAllTeacherByMajor(Long idMajor) {
        return userRepository.getAllTeacherByMajor(idMajor);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("Tài khoản không tồn tại");
        }
        return user.get();
    }

    @Override
    public User updateUser(UserDTO userDTO, Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("Tài khoản không tồn tại");
        }
        User newUser = new User();
        ConvertObject.convertUserDTOToUser(userDTO,newUser);
        newUser.setPassword(user.get().getPassword());
        newUser.setId(user.get().getId());
        return userRepository.save(newUser);
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("Tài khoản không tồn tại");
        }
        for(Role role: user.get().getRoless()){
            userRepository.deleteRecordInRoleUser(id, role.getId());
        }
        userRepository.delete(user.get());
        return "Delete success";
    }

    @Override
    public User changePassword(Long id, PasswordDTO passwordDTO) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("Người dùng không tồn tại");
        }
        user.get().setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
        User newUser = userRepository.save(user.get());
        return newUser;
    }

    @Override
    public String forgotPass(UsernameDTO usernameDTO) {
        User user = userRepository.findByUsername(usernameDTO.getUsername());
        if(user == null){
            throw new NotFoundException("Không tìm thấy thông tin tài khoản này");
        }
        String password = GenaralDataUser.generatePassword();
        StringBuilder content = new StringBuilder("Mât khẩu mới là:  ");
        content.append(password);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        mailService.sendMailWithText("Mật khẩu mới cho tài khoản "+ user.getUsername(), content.toString(), user.getEmail());
        return "Hệ thống đã cập nhật mật khẩu mới. Vui lòng vào email để kiểm tra!";
    }

//    @Override
//    public String uploadAvatar(MultipartFile multipartFile,Long id) throws IOException {
//        Optional<User> user = userRepository.findById(id);
//        if(user.isEmpty()){
//            throw new NotFoundException("User is not found");
//        }
//        String url = cloudinary.uploader()
//                .upload(multipartFile.getBytes(),
//                        Map.of("public_id", UUID.randomUUID().toString()))
//                .get("url")
//                .toString();
//        user.get().setAvatar(url);
//        userRepository.save(user.get());
//        return url;
//    }
}
