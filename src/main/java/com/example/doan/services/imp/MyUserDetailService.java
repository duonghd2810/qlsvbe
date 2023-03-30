package com.example.doan.services.imp;

import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.User;
import com.example.doan.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new NotFoundException("Not found user by username: "+ username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoless().forEach(item->{
            grantedAuthorities.add(new SimpleGrantedAuthority(item.getRoleName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthorities);
    }
}
