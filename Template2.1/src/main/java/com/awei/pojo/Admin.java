package com.awei.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    //id
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //角色
    private String role;
}
