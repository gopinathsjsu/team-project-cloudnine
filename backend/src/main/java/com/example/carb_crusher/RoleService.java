package com.example.carb_crusher;

import java.util.List;

public interface RoleService {
    Role save(Role role);
    Role findByName(String name);
    List<Role> findAll();
}

