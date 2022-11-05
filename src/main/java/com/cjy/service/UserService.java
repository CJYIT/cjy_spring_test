package com.cjy.service;

import com.cjy.domain.User;

import java.util.List;

public interface UserService {
    List<User> list();

    void save(User user, Long[] roleIds);

    void del(Long userId);

    //系统用户登录
    User login(String username, String password);

    //普通用户登录的接口，我这里实体类使用同一个了
    User Ptlogin(String username, String password);

    void edit(User userId, Long[] roleIds);

    void save(User user);
//    User login(String username, String password);
}