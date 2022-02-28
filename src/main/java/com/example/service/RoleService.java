package com.example.service;

import com.example.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> list();

    void save(Role role);
}
