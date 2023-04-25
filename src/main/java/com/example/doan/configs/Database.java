package com.example.doan.configs;

import com.example.doan.dtos.RoleDTO;
import com.example.doan.models.Role;
import com.example.doan.repositories.RoleRepository;
import com.example.doan.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Autowired
    private IRoleService iRoleService;
    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if(roleRepository.findByRoleName("TEACHER") == null){
                    Role roleTea =  iRoleService.createRole(new RoleDTO("TEACHER"));
                    roleRepository.save(roleTea);
                }
                if(roleRepository.findByRoleName("STUDENT") == null){
                    Role roleStu =  iRoleService.createRole(new RoleDTO("STUDENT"));
                    roleRepository.save(roleStu);
                }
                if(roleRepository.findByRoleName("ADMIN") == null){
                    Role roleAd = iRoleService.createRole(new RoleDTO("ADMIN"));
                    roleRepository.save(roleAd);
                }
            }
        };
    }
}
