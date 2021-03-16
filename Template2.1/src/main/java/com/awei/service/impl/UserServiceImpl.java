package com.awei.service.impl;

import com.awei.dao.UserMapper;
import com.awei.pojo.User;
import com.awei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;
    @Autowired
    JavaMailSenderImpl mailSender;

    @Override
    public int addUser(User user) {
        return mapper.addUser(user);
    }

    @Override
    public List<User> getUsers() {
        return mapper.getUsers();
    }

    @Override
    public void sendEmail(String email, String subject, String text) {
        //一个简单的邮件
        SimpleMailMessage message = new SimpleMailMessage();
        //标题
        message.setSubject(subject);
        //内容
        message.setText(text);
        //发往
        message.setTo(email);
        //发送者
        message.setFrom("90985300@qq.com");

        mailSender.send(message);
    }

    @Override
    public User getUserByLogin(User user) {
        return mapper.getUserByLogin(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return mapper.getUserByUsername(username);
    }

    @Override
    public int updateInfo(User user) {
        return mapper.updateInfo(user);
    }
}
