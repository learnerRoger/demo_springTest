package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<User> findAll() {
        List<User> userList = jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public Long save(User user) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getPhoneNum());


                return preparedStatement;
            }
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator,generatedKeyHolder);
        long longValue = generatedKeyHolder.getKey().longValue();
        //jdbcTemplate.update("insert into sys_user values (?,?,?,?,?)",null,user.getUsername(),user.getEmail(),user.getPassword(),user.getPhoneNum());
        return longValue;
    }

    @Override
    public void saveUserRoleRel(Long userId, Long[] roleIds) {
        for (Long roleId : roleIds){
            jdbcTemplate.update("insert into sys_user_role values (?,?)",userId,roleId);
        }
    }

    @Override
    public void delUserRoleRel(Long userId) {
        jdbcTemplate.update("delete from sys_user_role where userId = ?",userId);
    }

    @Override
    public void del(Long userId) {
        jdbcTemplate.update("delete from sys_user where id = ?",userId);
    }


}
