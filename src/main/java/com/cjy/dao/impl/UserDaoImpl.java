package com.cjy.dao.impl;

import com.cjy.dao.UserDao;
import com.cjy.domain.User;
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

    public List<User> findAll() {
        List<User> userList = jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }
//修改用户
    public void edit(Long userId, Long[] roleIds) {
        for (Long roleId : roleIds) {
            jdbcTemplate.update("insert into sys_user_role values(?,?)",userId,roleId);
        }
    }

    //普通用户登录
    @Override
    public User PtloginfindByUsernameAndPassword(String username, String password) {
        User user = jdbcTemplate.queryForObject("select * from user where username=? and password=?",
                new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }

    public Long save(final User user) {
        //创建PreparedStatementCreator
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                使用原始jdbc完成有个PreparedStatement的组建
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into sys_user values(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2,user.getUsername());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4,user.getPassword());
                preparedStatement.setString(5,user.getPhoneNum());
                preparedStatement.setString(6,user.getImg());
                return preparedStatement;
            }

            /*public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //使用原始jdbc完成有个PreparedStatement的组建
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2,user.getUsername());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4,user.getPassword());
                preparedStatement.setString(5,user.getPhoneNum());
                return preparedStatement;
            }*/
        };
        //创建keyHolder,keyHolder能获得对应生成的主键
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();//GeneratedKeyHolder,提供了一个可以返回新增记录对应主键值的方法
        jdbcTemplate.update(creator,keyHolder);
        //获得生成的主键
        long userId = keyHolder.getKey().longValue();
        return userId;
        /*GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(creator,keyHolder);
        //获得生成的主键
        long userId = keyHolder.getKey().longValue();
        return userId; //返回当前保存用户的id 该id是数据库自动生成的*/
    }

    public void saveUserRoleRel(Long userId, Long[] roleIds) {
//        一个用户userId有多个角色roleId,所以对集合roleIds进行循环遍历写入数据库
        for (Long roleId : roleIds) {
            jdbcTemplate.update("insert into sys_user_role values(?,?)",userId,roleId);
        }
    }

    public void delUserRoleRel(Long userId) {
        jdbcTemplate.update("delete from sys_user_role where userId=?",userId);
    }

    public void del(Long userId) {
        jdbcTemplate.update("delete from sys_user where id=?",userId);
    }
    /*BeanPropertyRowMapper
    将数据库查询结果转换为Java类对象。 常应用于使用Spring的JdbcTemplate查询数据库，
    获取List结果列表，数据库表字段和实体类自动对应。*/

    //系统用户登录
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = jdbcTemplate.queryForObject("select * from sys_user where username=? and password=?",
                new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }

    /*public User findByUsernameAndPassword(String username, String password) throws EmptyResultDataAccessException{
        User user = jdbcTemplate.queryForObject("select * from sys_user where username=? and password=?", new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }*/
}
