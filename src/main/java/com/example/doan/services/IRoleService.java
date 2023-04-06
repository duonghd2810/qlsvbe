package com.example.doan.services;

import com.example.doan.dtos.RoleDTO;
import com.example.doan.models.Role;

import java.util.List;

public interface IRoleService {
    Role getRoleById(Long id);
    Role createRole(RoleDTO roleDTO);
    List<Role> getListRole();
    Role updateRole(RoleDTO roleDTO, Long id);
    String deleteRole(Long id);
}
