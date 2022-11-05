package com.cjy.dao;

import com.cjy.domain.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAll();

    List<Role> findRoleByUserId(Long id);

    void save(Role role);

    void delUserRoleRel(Long roleId);

    void del(Long roleId);
}
