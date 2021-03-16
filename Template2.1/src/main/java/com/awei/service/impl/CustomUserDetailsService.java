package com.awei.service.impl;

import com.awei.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    //密码加密
    @Autowired
    PasswordEncoder passwordEncoder; //这里导入的密码加密 一会在config中注册 (@Bean)

    @Autowired
    AdminServiceImpl adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找这个用户
        Admin admin = adminService.getUser(username);
        //如果这个用户不存在则抛出一个异常
        if (admin == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 得到用户角色
        String role = admin.getRole();
        // 角色集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 角色必须以`ROLE_`开头，数据库中没有，则在这里加
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        //将用户信息放到User中(这个User是Security中的)
        return new User(
                admin.getUsername(),
                // 因为数据库是明文，所以这里需加密密码
                passwordEncoder.encode(admin.getPassword()),
                authorities
        );
    }
}
