package com.awei.dao;

import com.awei.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return
     */
    Admin getUser(@Param("username") String username);
}
