package com.awei.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class SendEmail {

    @Autowired
    JavaMailSenderImpl mailSender;

    /**
     * 发送邮件
     *
     * @param email 邮件
     * @param subject 主题
     * @param text 内容
     */
    @Async
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
        message.setFrom("903985300@qq.com");

        mailSender.send(message);
    }
}
