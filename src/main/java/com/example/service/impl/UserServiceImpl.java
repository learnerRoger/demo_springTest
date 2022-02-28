package com.example.service.impl;

import com.example.dao.RoleDao;
import com.example.dao.UserDao;
import com.example.dao.impl.RoleDaoImpl;
import com.example.dao.impl.UserDaoImpl;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> list() {
        List<User> userList = userDao.findAll();
        for (User user : userList){
            Long id = user.getId();
            List<Role> roleList = roleDao.findRoleByUserId(id);
            user.setRoles(roleList);
        }
        return userList;
    }

    @Override
    public void save(User user, Long[] roleIds) {
        Long userId = userDao.save(user);
        userDao.saveUserRoleRel(userId,roleIds);
    }

    @Override
    public void del(Long userId) {
        userDao.delUserRoleRel(userId);
        userDao.del(userId);
    }


    public void setRoleDao(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }
}
