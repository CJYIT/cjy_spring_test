package com.cjy.dao;

import com.cjy.domain.User;

import java.util.List;

public interface UserDao {
    Long save(User user);

    void saveUserRoleRel(Long id, Long[] roleIds);

    void delUserRoleRel(Long userId);

    void del(Long userId);
    //系统用户登录
    User findByUsernameAndPassword(String username, String password);

    List<User> findAll();

    void edit(Long id, Long[] roleIds);

    User PtloginfindByUsernameAndPassword(String username, String password);
//    User findByUsernameAndPassword(String username, String password);
}
