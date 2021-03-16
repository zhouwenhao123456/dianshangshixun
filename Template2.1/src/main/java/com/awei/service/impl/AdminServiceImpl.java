package com.awei.service.impl;

import com.awei.dao.AdminMapper;
import com.awei.pojo.Admin;
import com.awei.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper mapper;
    @Override
    public Admin getUser(String username) {
        return mapper.getUser(username);
    }
}
