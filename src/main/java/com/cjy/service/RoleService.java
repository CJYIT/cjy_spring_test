package com.cjy.service;

import com.cjy.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> list();

    void save(Role role);

    void del(Long roleId);
}
