package com.cjy.dao.impl;

import com.cjy.dao.BaseDao;
import org.springframework.jdbc.core.JdbcTemplate;

/****
 * @Author:cjy
 * @Description: com.cjy.dao.impl
 * @Date 2022/10/6 11:23
 *****/
public class BaseDaoImpl implements BaseDao {
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
