package com.awei.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    //id
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //电话
    private String phone;
    //Email
    private String email;
    //个人简介
    private String info;
    //所有订单
    private List<Order> order;

}
