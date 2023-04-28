package com.example.doan.configs;

import com.example.doan.dtos.RoleDTO;
import com.example.doan.models.Classroom;
import com.example.doan.models.DayOfTheWeek;
import com.example.doan.models.Role;
import com.example.doan.repositories.ClassRoomRepository;
import com.example.doan.repositories.DayOfWeekRepository;
import com.example.doan.repositories.RoleRepository;
import com.example.doan.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class Database {

    @Autowired
    private IRoleService iRoleService;
    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository, ClassRoomRepository classRoomRepository, DayOfWeekRepository dayOfWeekRepository){
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
                if(classRoomRepository.findAll().size() == 0){
                    Classroom c1 = new Classroom("P01");
                    Classroom c2 = new Classroom("P02");
                    Classroom c3 = new Classroom("P03");
                    Classroom c4 = new Classroom("P04");
                    Classroom c5 = new Classroom("P05");
                    Classroom c6 = new Classroom("P06");
                    Classroom c7 = new Classroom("P07");
                    Classroom c8 = new Classroom("P08");
                    Classroom c9 = new Classroom("P09");
                    Classroom c10 = new Classroom("P10");
                    List<Classroom> classroomList = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10);
                    classRoomRepository.saveAll(classroomList);
                }
                if(dayOfWeekRepository.findAll().size() == 0){
                    DayOfTheWeek d1 = new DayOfTheWeek("Thứ 2");
                    DayOfTheWeek d2 = new DayOfTheWeek("Thứ 3");
                    DayOfTheWeek d3 = new DayOfTheWeek("Thứ 4");
                    DayOfTheWeek d4 = new DayOfTheWeek("Thứ 5");
                    DayOfTheWeek d5 = new DayOfTheWeek("Thứ 6");
                    DayOfTheWeek d6 = new DayOfTheWeek("Thứ 7");
                    DayOfTheWeek d7 = new DayOfTheWeek("Chủ nhật");
                    dayOfWeekRepository.saveAll(Arrays.asList(d1,d2,d3,d4,d5,d6,d7));
                }
            }
        };
    }
}
