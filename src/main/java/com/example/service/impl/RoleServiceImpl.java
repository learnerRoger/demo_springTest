package com.example.service.impl;

import com.example.dao.RoleDao;
import com.example.domain.Role;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

//    public void setRoleDao(RoleDao roleDao) {
//        this.roleDao = roleDao;
//    }

    public List<Role> list(){
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
}
