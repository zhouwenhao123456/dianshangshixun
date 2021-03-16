package com.awei.controller;

import com.awei.pojo.Order;
import com.awei.pojo.Product;
import com.awei.pojo.User;
import com.awei.util.SendEmail;
import com.awei.service.impl.OrderServiceImpl;
import com.awei.service.impl.ProductServiceImpl;
import com.awei.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商家处理
 */
@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    ProductServiceImpl productService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SendEmail sendEmail;

    /**
     * 订单请求
     *
     * @return 客户订单信息
     */
    @GetMapping("/order")
    public String order(Model model){
        //查询所有订单
        List<Order> orders = orderService.getOrders();
        model.addAttribute("orders",orders);
        return "business/order";
    }

    /**
     * 客户信息的请求
     *
     * @param model
     * @return 客户信息页面
     */
    @GetMapping("/customer")
    public String customer(Model model){
        //查询所有客户及其所有订单
        List<User> users = userService.getUsers();
        //返回数据给前端
        model.addAttribute("users",users);
        //回到前端页面
        return "business/customer";
    }

    /**
     * 产品信息请求
     *
     * @param model
     * @return 产品页面
     */
    @GetMapping("/product")
    public String product(Model model){
        //查询
        List<Product> products = productService.getProducts();
        model.addAttribute("products",products);
        return "business/product";
    }

    /**
     * 拒绝订单处理
     *
     * @param no 订单编号
     * @return 订单页面
     */
    @GetMapping("/deleteOrder/{no}")
    public String deleteOrder(@PathVariable("no")int no){
//        //删除
//        orderService.deleteOrder(no);
        //将订单状态改为已拒绝
        orderService.acceptOrder(no,false,4);

        return "redirect:/business/order";
    }

    /**
     * 接受订单处理
     *
     * @param no 订单编号
     * @return 订单页面
     */
    @GetMapping("/acceptOrder/{no}")
    public String acceptOrder(@PathVariable("no")int no){
        //订单状态变为待发货,商家已接单
        orderService.acceptOrder(no,true,1);

        return  "redirect:/business/order";
    }

    /**
     * 前往发送页面的请求
     *
     * @param email 要发送的邮件
     * @param model
     * @return 邮件发送页面
     */
    @GetMapping("/sendEmail/{email}")
    public String sendEmail(@PathVariable("email")String email,Model model){
        model.addAttribute("email",email);
        return "business/sendEmail";
    }

    /**
     * 邮件发送处理
     *
     * @param email 发送的邮件
     * @param subject 邮件主题
     * @param text 邮件内容
     * @return 重定向到客户页面
     */
    @PostMapping("/sendEmail")
    public String sendEmail2(
            @RequestParam("email")String email,
            @RequestParam("subject")String subject,
            @RequestParam("text")String text){

        sendEmail.sendEmail(email,subject,text);

        return "redirect:/business/customer";
    }
}
