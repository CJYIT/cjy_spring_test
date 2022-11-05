package com.cjy.dao.impl;

import com.cjy.dao.RoleDao;
import com.cjy.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//<Role>(Role.class)实体的类型和实体的字节码
    public List<Role> findAll() {
        List<Role> roleList = jdbcTemplate.query("select * from sys_role", new BeanPropertyRowMapper<Role>(Role.class));
        return roleList;
    }

    @Override
    public List<Role> findRoleByUserId(Long id) {
//        从中间表别名ur开始查询到角色表id,找到中间id表与角色表对应id的行ur.roleId=r.id,这个id是userservice传来得user表id
        List<Role> roles = jdbcTemplate.query("select * from sys_user_role ur,sys_role r " +
                "where ur.roleId=r.id and ur.userId=?", new BeanPropertyRowMapper<Role>(Role.class), id);
        return roles;
    }

//    @Override
//    public List<Role> findRoleByUserId(Long id) {
//        List<Role> roles = jdbcTemplate.query("select * from sys_user_role ur,sys_role r where ur.roleId=r.id and ur.userId=?", new BeanPropertyRowMapper<Role>(Role.class), id);
//        return roles;
//    }

    public void save(Role role) {
        //每次添加数据前设置主键自增1，避免了因为删除数据后主键id不按顺序排列，这个只能从最后一个自增
//        jdbcTemplate.update("alter table sys_role auto_increment = 1");
        //重新排序，删除数据后重新排序数据，先增添数据后再重新排序，因为增添数据的动作比排序块
        /*jdbcTemplate.update("update sys_role as t1 join (select id,(@rowno:=@rowno+1) as rowno from sys_role a," +
                "(select (@rowno:=0)) b order by a.id) as t2 SET t1.id=t2.rowno WHERE t1.id=t2.id;");*/
          //本来是三个参数，但是主键是自增的不用写或者写null，这里插入三个参数
        jdbcTemplate.update("insert into sys_role values(?,?,?)",null,role.getRoleName(),role.getRoleDesc());
    }

    public void delUserRoleRel(Long roleId) {
        jdbcTemplate.update("delete from sys_role where id=?",roleId);
    }

    public void del(Long roleId) {
        jdbcTemplate.update("delete from sys_user where id=?",roleId);
    }
}
