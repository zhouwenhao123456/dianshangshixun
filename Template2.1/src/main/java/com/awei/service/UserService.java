package com.awei.service;

import com.awei.pojo.User;
import java.util.List;

public interface UserService {
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
     * 发送邮件
     *
     * @param email 邮件
     * @param subject 主题
     * @param text 内容
     */
    void sendEmail(String email, String subject, String text);
    /**
     * 登录验证
     *
     * @param user 用户实体
     * @return 用户
     */
    /*
    * 查询用户名是否存在
    * */

    User getUserByLogin(User user);
    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户
     */
    User getUserByUsername(String username);

    /**
     * 用户修改个人信息
     *
     * @param user 用户实体
     * @return 修改成功的条数
     */
    int updateInfo(User user);
}
