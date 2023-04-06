package com.example.doan.services.imp;

import com.example.doan.dtos.RoleDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.Role;
import com.example.doan.repositories.RoleRepository;
import com.example.doan.services.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Role getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isEmpty()){
            throw new NotFoundException("Role is not found");
        }
        return role.get();
    }

    @Override
    public Role createRole(RoleDTO roleDTO) {
        Role role = this.modelMapper.map(roleDTO,Role.class);
        Role newRole = roleRepository.save(role);
        return newRole;
    }

    @Override
    public List<Role> getListRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(RoleDTO roleDTO, Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isEmpty()){
            throw new NotFoundException("Role is not found");
        }
        Role newRole = this.modelMapper.map(roleDTO,Role.class);
        newRole.setId(role.get().getId());
        return roleRepository.save(newRole);
    }

    @Override
    public String deleteRole(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isEmpty()){
            throw new NotFoundException("Role is not found");
        }
        roleRepository.delete(role.get());
        return "Delete success";
    }
}
