package com.example.doan.controllers;

import com.example.doan.bases.BaseController;
import com.example.doan.dtos.RoleDTO;
import com.example.doan.models.Role;
import com.example.doan.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController extends BaseController<Role> {
    @Autowired
    private IRoleService iRoleService;

    @GetMapping
    public ResponseEntity<?> getAllRole(){
        return this.resListSuccess(iRoleService.getListRole());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable(name = "id")Long id){
        return this.resSuccess(iRoleService.getRoleById(id));
    }
    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody RoleDTO roleDTO){
        return this.resSuccess(iRoleService.createRole(roleDTO));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> editRole(@RequestBody RoleDTO roleDTO,
                                      @PathVariable(name = "id")Long id){
        return this.resSuccess(iRoleService.updateRole(roleDTO,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable(name = "id")Long id){
       return this.resStringSuccess(iRoleService.deleteRole(id));
    }
}
