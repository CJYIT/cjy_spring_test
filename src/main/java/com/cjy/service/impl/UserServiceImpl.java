package com.cjy.service.impl;

import com.cjy.dao.RoleDao;
import com.cjy.dao.UserDao;
import com.cjy.domain.Role;
import com.cjy.domain.User;
import com.cjy.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private RoleDao roleDao;
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<User> list() {
        List<User> userList = userDao.findAll();
        //封装userList中的每一个User的roles数据
        for (User user : userList) {
//            获得每个user的id
            Long id = user.getId();
//            将id作为参数,查询当前userid对应的role集合数据,如id1为1的对应的是两个对象,院长和研究员
            //我们查询roledao就要在roledao中查询,所以创建private RoleDao roleDao;并在配置文件中配置
            List<Role> roles = roleDao.findRoleByUserId(id);//通过userid查询roleid,在dao层将方法findRoleByUserId(id)实现
            user.setRoles(roles);//对得到的数据roles进行封装

        }
        return userList;
        /*for (User user : userList) {
            //获得user的id
            Long id = user.getId();
            //将id作为参数 查询当前userId对应的Role集合数据
            List<Role> roles = roleDao.findRoleByUserId(id);
            user.setRoles(roles);
        }
        return userList;*/
    }
//添加用户
    public void save(User user, Long[] roleIds) {
        //第一步 向sys_user表中存储数据
        Long userId = userDao.save(user);
        //第二步 向sys_user_role 关系表中存储多条数据
        userDao.saveUserRoleRel(userId,roleIds);
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void del(Long userId) {
        //1、删除sys_user_role关系表
        userDao.delUserRoleRel(userId);
//        int i = 1/0;
        //2、删除sys_user表
        userDao.del(userId);
    }
    //系统用户登录
    @Override
    public User login(String username, String password) {
//        findByUsernameAndPassword用的是JPA中支持的关键词and的用法
        User user = userDao.findByUsernameAndPassword(username,password);
        return user;
    }

    @Override
    public User Ptlogin(String username, String password) {
        User user = userDao.PtloginfindByUsernameAndPassword(username,password);
        return user;
    }

    //修改用户信息
    public void edit(User user, Long[] roleIds) {
        //第一步 向sys_user表中存储数据
        Long userId = userDao.save(user);
        //第二步 向sys_user_role 关系表中存储多条角色数据
        userDao.saveUserRoleRel(userId,roleIds);
    }

//    添加图片上传的测试
    @Override
    public void save(User user) {
        userDao.save(user);
    }

 /*   public void edit(Long useId) {
        userDao.editUserRolRel(useId);
        userDao.editUser();
    }
*/
   /* public User login(String username, String password) {
        try {
            User user = userDao.findByUsernameAndPassword(username,password);
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }*/


}
