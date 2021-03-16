package com.awei.controller;

import com.awei.pojo.Order;
import com.awei.pojo.Product;
import com.awei.pojo.ShoppingCar;
import com.awei.pojo.User;
import com.awei.service.impl.OrderServiceImpl;
import com.awei.service.impl.ProductServiceImpl;
import com.awei.service.impl.ShoppingCarServiceImpl;
import com.awei.service.impl.UserServiceImpl;
import com.awei.util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ViewController {

    @Autowired
    ProductServiceImpl productService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShoppingCarServiceImpl shoppingCarService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    SendEmail sendEmail;

    //手机号的正则表达式
    private static String phoneRegex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    //邮箱正则表达式
    private static String emailRegex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * 前往用户主页的请求
     *
     * @return 用户主页
     */
    @GetMapping({"/index","/"})
    public String index(){
        return "view/index";
    }

    /**
     * 所有商品展示请求
     *
     * @param model
     * @return 所有商品展示
     */
    @GetMapping("/product")
    public String product(Model model){
        //查询所有商品
        List<Product> products = productService.getProducts();
        model.addAttribute("products",products);
        return "view/product";
    }
    //自己写的测试注册类
//    @GetMapping("/regist")
//    public String regist(User user,Model model,HttpSession session){
//       User usr=userService.getUserByUsername(user.getUsername());
//       if(user==null){
//           userService.addUser(user);
//       }
//       else {
//           //将这个用户的信息返回给前端
//           model.addAttribute("注册失败，请重试");
//           return "view/login";
//       }
//       return "view/login";
//    }
    /**
     * 前往登录界面请求
     *
     * @return 登陆界面
     */
    @GetMapping("/login")
    public String toLogin(){
        return "view/login";
    }

    /**
     * 用户登录验证
     *
     * @param user 用户实体
     * @param model
     * @param session
     * @return 成功则跳转都个人信息页面，失败返回登陆页面并提示失败信息
     */
    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session){
        //对用户的密码进行加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //根据用户名和密码查询用户
        User login = userService.getUserByLogin(user);
        //调用BCrypt加密算法匹配机制，进行密码匹配
        if (!encoder.matches(user.getPassword(),login.getPassword())){
            model.addAttribute("msg","用户名或密码错误！");
            return "view/login";
        }
        //给用户一个Session
        session.setAttribute("username",user.getUsername());
        //将这个用户的信息返回给前端
        model.addAttribute("user",login);
        return "view/person";
    }

    /**
     * 像购物车添加商品处理
     *
     * @param proName 商品名称
     * @param money 金额
     * @param session
     * @return 全部商品界面
     */
    @GetMapping("/addProduct/{proName}/{money}")
    public String addShoppingCar(@PathVariable("proName")String proName, @PathVariable("money")double money, HttpSession session){
        //获取当前登录的用户
        String username = (String) session.getAttribute("username");
        //如果用户不存在就转到登录页面
        if (username==null){
            return "view/login";
        }
        //添加
        shoppingCarService.addProduct(username,proName,money);
        return "redirect:/product";
    }
    /**
     * 注销处理
     *
     * @param session 用户Session
     * @return 用户首页
     */
    @GetMapping("/toLogout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }

    /**
     * 用户查看购物车处理
     *
     * @param session 用户Session
     * @param model
     * @return 购物车页面
     */
    @GetMapping("/getShoppingCar")
    public String getShoppingCar(HttpSession session,Model model){
        //获取用户
        String username = (String) session.getAttribute("username");
        //查询用户购物车
        List<ShoppingCar> shoppingCars = shoppingCarService.getShoppingCars(username);
        //返回数据给前端
        model.addAttribute("shoppingCars",shoppingCars);
        if(shoppingCars.size()==0){
            model.addAttribute("msg","购物车空空如也！");
        }
        return "view/shoppingCar";
    }

    /**
     * 查找处理
     *
     * @param search 查找条件
     * @param model
     * @return 查找结果
     */
    @GetMapping("/search")
    public String search(String search,Model model){

        //Mybatis不支持重载

        //定义一个标记
        boolean flag = false;
        //直接查询名字
//        Product byProName = productService.getProductBySearch2(search);
        List<Product> byLike = productService.getProductByLike("%" + search + "%");
        model.addAttribute("byLike",byLike);
        try {
            //转换成数字，如果是金额便成功并查询，如果不是捕获异常继续执行
            double money = Double.parseDouble(search);
            List<Product> byMoney = productService.getProductBySearch(money);
            model.addAttribute("byMoney",byMoney);
            //如果金额查找有商品则标记为true
            if(byMoney.size() > 0){
                flag = true;
            }
        }catch(Exception e){
        }
        //如果名称/金额查找有一样有结果则展示，否则显示提示信息没有找到
        if(byLike.size()!=0 || flag){
            return "view/search";
        }

//        System.out.println("byLike.size()===>" + byLike.size());
//        System.out.println("flag===>" + flag);

       model.addAttribute("msg","抱歉，没有找到商品！");
        return "view/search";
    }

    /**
     * 分类展示处理
     *
     * @param type 类别编号
     * @param model
     * @return 类别商品
     */
    @GetMapping("/getProductByType/{type}")
    public String getProductByType(@PathVariable("type")int type,Model model){
        //查询商品
        List<Product> products = productService.getProductByType(type);
        //返回数据给前端
        model.addAttribute("products",products);
        //如果没有商品则返回提示信息
        if(products.size() == 0){
            model.addAttribute("msg","抱歉，没有找到商品！");
        }
        return "view/type";
    }

    /**
     * 删除购物车中商品
     *
     * @param id 购物车商品id
     * @return 购物车页面
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){

        shoppingCarService.deleteProduct(id);

        return "redirect:/getShoppingCar";
    }

    /**
     * 个人主页请求
     *
     * @param session 用户Session
     * @param model
     * @return 用户个人信息页面
     */
    @GetMapping("/personInfo")
    public String personInfo(HttpSession session,Model model){
        //获取当前登录用户用户名
        String username = (String) session.getAttribute("username");
        //查询用户
        User user = userService.getUserByUsername(username);
        //返回用户给前端
        model.addAttribute("user",user);
        return "view/person";
    }

    /**
     * 前往填写地址页面
     *
     * @param id 要下单的购物车id
     * @param model
     * @return 添加地址页面
     */
    @GetMapping("/ok/{id}")
    public String ok(@PathVariable("id")int id,Model model){

        ShoppingCar product = shoppingCarService.getProductById(id);
        model.addAttribute("product",product);

        return "view/addr";
    }

    /**
     * 用户下单处理
     *
     * @param order 订单实体
     * @param id 购物车id
     * @return 用户购物车页面
     */
    @PostMapping("/ok")
    public String ok2(Order order, @RequestParam("id")int id){

        order.setOrderState(0);
        //支付状态为已支付
        order.setPayState(true);

        //添加订单
        orderService.addOrder(order);
        //之后删除购物车
        shoppingCarService.deleteProduct(id);
        return "redirect:/getShoppingCar";
    }

    /**
     * 用户查看订单请求处理
     *
     * @param session 用户Session
     * @param model
     * @return 用户订单页面
     */
    @GetMapping("/order")
    public String order(HttpSession session,Model model){
        String username = (String) session.getAttribute("username");
        List<Order> orders = orderService.getOrderByUsername(username);
        model.addAttribute("orders",orders);
        if(orders.size()==0){
            model.addAttribute("msg","赶快下单吧！");
        }
        return "view/order";
    }

    /**
     * 用户签收
     *
     * @param no 订单编号
     * @return 订单页面
     */
    @GetMapping("/isTrue/{no}")
    public String isTrue(@PathVariable("no")int no){
        //将订单编号修改为已签收
        orderService.acceptOrder(no,true,3);
        return "redirect:/order";
    }
    /**
     * 注册功能处理
     *
     * @param user 用户实体
     * @return 登录页面
     */
//    注册功能处理
    @PostMapping("/logon")
    @ResponseBody
    public String logon2(User user,Model model){
        //进行判断,如果不正确,返回注册页面并提示错误信息
        if(!Pattern.compile(emailRegex).matcher(user.getEmail()).find()){
            return "1";
        }

        if(!Pattern.compile(phoneRegex).matcher(user.getPhone()).find()){
            return "2";
        }
        //对用户的密码进行加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //添加用户
        userService.addUser(user);
        //给用户发邮件欢迎成为梦想无限用户
        String subject = "Hello " + user.getUsername() + " !";
        String text = "欢迎您注册成为我们梦想无限的客户，我们将用心服务，真诚待人。祝您购物愉快！";
        sendEmail.sendEmail(user.getEmail(),subject,text);
        return "3";
    }

    /**
     * 前往忘记密码页面
     *
     * @return 忘记密码页面
     */
    @GetMapping("/forget")
    public String forget(){
        return "view/forget";
    }

    /**
     * 忘记密码处理
     *
     * @param username 用户名
     * @return 登录页
     */
    @PostMapping("/forget")
    public String forget2(@RequestParam("username") String username,Model model){
        User user = userService.getUserByUsername(username);
        if(user==null){
            model.addAttribute("msg","用户不存在！");
            return "view/forget";
        }
        String subject = "密码找回";
        String text = "您的密码为:---" + user.getPassword() + "---请妥善保管好您的密码！";
        sendEmail.sendEmail(user.getEmail(),subject,text);
        return "view/login";
    }

    /**
     * 跳转修改个人信息页面请求
     *
     * @param session 用户Session
     * @param model
     * @return 跳转修改个人信息页面
     */
    @GetMapping("/updateInfo")
    public String updateInfo(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        model.addAttribute("user",user);
        return "view/updateInfo";
    }

    /**
     * 修改个人信息处理
     *
     * @param user 用户实体
     * @return 失败返回错误信息提示，成功返回登陆界面
     */
    @PostMapping("/updateInfo")
    public String updateInfo2(User user){
        //进行判断,如果不正确,返回注册页面并提示错误信息
        if(!Pattern.compile(emailRegex).matcher(user.getEmail()).find()){
            return "redirect:/updateInfoEmail";
        }
        if(!Pattern.compile(phoneRegex).matcher(user.getPhone()).find()){
            return "redirect:/updateInfoPhone";
        }
        System.out.println(user);
        userService.updateInfo(user);
        System.out.println(user);
        return "redirect:/personInfo";
    }
    @GetMapping("/updateInfoEmail")
    public String updateInfoEmail(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        model.addAttribute("user",user);
        model.addAttribute("msg","邮箱错误！");
        return "view/updateInfo";
    }
    @GetMapping("/updateInfoPhone")
    public String updateInfoPhone(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        model.addAttribute("user",user);
        model.addAttribute("msg","手机号不正确！");
        return "view/updateInfo";
    }


    //--------------------------------

    /**
     * 后台登录页面
     *
     * @return
     */
//    @GetMapping({"/toLogin","admin"})
//    public String login(HttpSession session){
//        List<Product> products=productService.getProducts();
//        session.setAttribute("products",products);
//        return "admin/role";
//    }
    @GetMapping({"/toLogin","admin"})
    public String login(){
        return "login";
    }

    /**
     * 后台首页
     *
     * @return
     */
    @GetMapping({"/background/index"})
    public String backgroundIndex(){
        return "index";
    }

}
