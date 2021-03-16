package com.awei.service;

import com.awei.pojo.Admin;


public interface AdminService {
    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return
     */
    Admin getUser(String username);
}
