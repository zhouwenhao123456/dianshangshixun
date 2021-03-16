package com.awei.dao;


import com.awei.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 添加一个用户
     *
     * @param user 用户对象
     * @return 添加成功的条数
     */
    int addUser(User user);

    /**
     * 查询所有用户及其所有订单
     *
     * @return 所有用户和所拥有的订单
     */
    List<User> getUsers();

    /**
     * 登录验证
     *
     * @param user 用户实体
     * @return 用户
     */
    User getUserByLogin(User user);



    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户
     */
    User getUserByUsername(@Param("username") String username);

    /**
     * 用户修改个人信息
     *
     * @param user 用户实体
     * @return 修改成功的条数
     */
    int updateInfo(User user);
}
