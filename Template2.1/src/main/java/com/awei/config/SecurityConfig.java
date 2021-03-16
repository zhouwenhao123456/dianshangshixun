package com.awei.config;

import com.awei.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService userService;//装配你实现UserDetailsService接口的类
    @Autowired
    private DataSource dataSource;//数据源
    @Autowired
    private PersistentTokenRepository persistentTokenRepository;


    @Bean
    public PasswordEncoder passwordEncoder(){
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();//一种加密方式
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //授权
        //antMatchers()访问路径
        //permitAll()为所有用户
        //hasRole()指定什么角色可以访问
        http.authorizeRequests()
                .antMatchers("/root/**").hasRole("admin")
                .antMatchers("/business/**").hasRole("business")
                .antMatchers("/supplier/**").hasRole("supplier");

        //登录
        //.loginPage() 定制的登录页
        //默认表单name为username,password，
        //若表单name不同，通过.usernameParameter("user").passwordParameter("pwd").指定
        //表单的提交链接应该与loginPage的路径一致，否则要通过loginProcessingUrl()指定
        http.formLogin().loginPage("/toLogin").defaultSuccessUrl("/background/index");

        //防止网站工具
        //如果无法注销就关闭
        http.csrf().disable();//关闭csrf功能

        //注销功能
        //logoutSuccessUrl()注销成功后去的路径
        http.logout().logoutSuccessUrl("/admin");

        //记住我
        http.rememberMe()
                //设置数据源
                .tokenRepository(persistentTokenRepository)
                //.rememberMeParameter()
                //设置超时时间，默认是两周
                //.tokenValiditySeconds(60);
                //自定义登录逻辑，我没写
                //.userDetailsService(userDetailsService);
                 .userDetailsService(userService);


    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository()
    {
        JdbcTokenRepositoryImpl jdbcTokenRepository =new JdbcTokenRepositoryImpl();
        //设置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        //自动建表，注意：第一次启动时开启，第二次启动时注释掉
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());

    }
}

