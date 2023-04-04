package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController

@RequestMapping("/api/roles")

public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        // Add validation and error handling
        return roleService.save(role);
    }

    @GetMapping("/{name}")
    public Role findByName(@PathVariable String name) {
        // Add validation and error handling
        return roleService.findByName(name);
    }

    @GetMapping
    public List<Role> findAllRoles() {
        return roleService.findAll();
    }
}

