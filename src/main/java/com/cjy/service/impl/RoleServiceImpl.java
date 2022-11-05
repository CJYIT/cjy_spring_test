package com.cjy.service.impl;

import com.cjy.dao.RoleDao;
import com.cjy.domain.Role;
import com.cjy.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    //注入
    private RoleDao roleDao;
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> list() {
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }

    public void save(Role role) {
        roleDao.save(role);
    }

    public void del(Long roleId) {
        //1、删除sys_user_role关系表
        roleDao.delUserRoleRel(roleId);
        //2、删除sys_role表
        roleDao.del(roleId);
    }
}
