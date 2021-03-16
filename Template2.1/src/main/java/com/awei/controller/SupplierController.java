package com.awei.controller;


import com.awei.pojo.Order;
import com.awei.pojo.Product;
import com.awei.service.impl.OrderServiceImpl;
import com.awei.service.impl.ProductServiceImpl;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.util.List;

/**
 * 供货商处理
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    ProductServiceImpl productService;
    @Autowired
    OrderServiceImpl orderService;

    /**
     * 订单请求
     *
     * @return 发货订单信息
     */
    @GetMapping("/sporder")
    public String sporder(Model model){
        //查询所有订单
        List<Order> orders = orderService.getOrders();
        model.addAttribute("orders1",orders);
        return "supplier/sporder";
    }
    /**
     * 库存请求
     *
     * @return 商品库存信息
     */
    @GetMapping("/stock")
    public String stock(Model model){
        //查询所有
        List<Product> products = productService.getProducts();
        //放入
        model.addAttribute("products",products);

        return "supplier/stock";
    }
    /**
     * 添加商品页面请求
     *
     * @return 商品添加页面
     */
    @GetMapping("/addProduct")
    public String addProduct(String flag,Model model){

        if(flag!=null){
            if (flag.equals("1")){
                model.addAttribute("msg","商品已存在(名称重复)！");
            }else if(flag.equals("2")) {
                model.addAttribute("msg","数量不能少于0");
            }else if (flag.equals("3")){
                model.addAttribute("msg","请填写数量");
            }else if (flag.equals("4")){
                model.addAttribute("msg","金额不能少于0");
            }
        }

        return "supplier/addProduct";
    }

    /**
     * 添加商品处理
     *
     * @param product 商品实体
     * @param file 商品图片
     * @return 商品页面
     * @throws IOException 图片上传异常
     */
    @PostMapping("/addProduct")
    public String addProduct2(Product product, @RequestParam("file") MultipartFile file) throws IOException {

        //随机生成一个八位数字编号
        int no = (int) (Math.random() * 100000000);
        product.setNo(no);
        //添加
        try{
            if(product.getNum() < 0){
                return "redirect:/supplier/addProduct?flag=2";
            }
            if(product.getMoney() < 0){
                return "redirect:/supplier/addProduct?flag=4";
            }
            productService.addProduct(product);
        }catch (NullPointerException e) {
            return "redirect:/supplier/addProduct?flag=3";
        }catch (Exception e){
            return "redirect:/supplier/addProduct?flag=1";
        }
        //文件名为商品编号
        String uploadFilename = String.valueOf(product.getNo()) + ".jpg";

        //如果文件名为空，直接返回！
        if ("".equals(file.getOriginalFilename())){//获取文件名：file.getOriginalFilename();
            return "redirect:/supplier/stock";
        }
//        System.out.println("上传文件名 ：" + uploadFilename);

        //获取根目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) {
            path = new File("");
        }
        //创建静态文件目录  引用在配置文件中配置
        File upload = new File(path.getAbsolutePath(),"static/img/");
        if(!upload.exists()) {
            upload.mkdirs();
        }
        //文件输入流
        InputStream is = file.getInputStream();
        //文件输出流
        OutputStream os = new FileOutputStream(new File(upload,uploadFilename));

        //读取写出
        int len = 0;
        byte[] buffer = new byte[1024];
        while((len = is.read(buffer)) != -1){
            os.write(buffer,0,len);
            //flush()将输入流和输出流中的缓冲进行刷新，使缓冲区中的元素即时做输入和输出，而不必等缓冲区满
            os.flush();
        }

        //关闭流
        os.close();
        is.close();

        return "redirect:/supplier/stock";
    }

    /**
     * 下架商品处理
     *
     * @param no 要下架商品的编号
     * @return 商品展示页面
     */
    @GetMapping("/deleteProduct/{no}")
    public String deleteProduct(@PathVariable("no") int no){
        //下架
        productService.deleteProduct(no);

        return "redirect:/supplier/stock";
    }

    /**
     * 修改商品页面请求
     *
     * @param no 要修改商品编号
     * @param model
     * @return 修改商品页面
     */
    @GetMapping("/updateProduct/{no}")
    public String updateProduct(@PathVariable("no") int no, String flag, Model model){

        if(flag!=null){
            if (flag.equals("1")){
                model.addAttribute("msg","商品已存在(名称重复)！");
            }else if(flag.equals("2")) {
                model.addAttribute("msg","数量不能少于0");
            }else if (flag.equals("3")){
                model.addAttribute("msg","请填写数量");
            }else if (flag.equals("4")){
                model.addAttribute("msg","金额不能少于0");
            }
        }

        //查询
        Product product = productService.getProductByNo(no);
        //放入
        model.addAttribute("product",product);

        return "supplier/updateProduct";
    }

    /**
     * 商品修改处理
     *
     * @param product 商品实体
     * @return 商品展示页面
     */
    @PostMapping("/updateProduct")
    public String updateProduct2(Product product, @RequestParam("file") MultipartFile file) throws IOException {
        try{
            if(product.getNum() < 0){
                return "redirect:/supplier/updateProduct/" + product.getNo() + "?flag=2";
            }
            if(product.getMoney() < 0){
                return "redirect:/supplier/updateProduct/" + product.getNo() + "?flag=4";
            }
            productService.updateProduct(product);
        }catch (NullPointerException e) {
            return "redirect:/supplier/updateProduct/" + product.getNo() + "?flag=3";
        }catch (Exception e){
            return "redirect:/supplier/updateProduct/" + product.getNo() + "?flag=1";
        }

        //文件名为商品编号
        String uploadFilename = String.valueOf(product.getNo()) + ".jpg";

        //如果文件名为空，直接返回！
        if ("".equals(file.getOriginalFilename())){//获取文件名：file.getOriginalFilename();
            return "redirect:/supplier/stock";
        }
//        System.out.println("上传文件名 ：" + uploadFilename);
        //获取根目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) {
            path = new File("");
        }
        //创建静态文件目录  引用在配置文件中配置
        File upload = new File(path.getAbsolutePath(),"static/img/");
        if(!upload.exists()) {
            upload.mkdirs();
        }
        //文件输入流
        InputStream is = file.getInputStream();
        //文件输出流
        OutputStream os = new FileOutputStream(new File(upload,uploadFilename));

        //读取写出
        int len = 0;
        byte[] buffer = new byte[1024];
        while((len = is.read(buffer)) != -1){
            os.write(buffer,0,len);
            //flush()将输入流和输出流中的缓冲进行刷新，使缓冲区中的元素即时做输入和输出，而不必等缓冲区满
            os.flush();
        }

        //关闭流
        os.close();
        is.close();

        return "redirect:/supplier/stock";
    }

    /**
     * 发货处理
     *
     * @param no 订单编号
     * @return 订单页面
     */
    @GetMapping("/send/{no}")
    public String send(@PathVariable("no")int no,Model model){

        //查询该商品库存
        Order orderByNo = orderService.getOrderByNo(no);
        Product product = productService.getProductBySearch2(orderByNo.getProName());
        int num = product.getNum();
        //如果库存不足1个则请求转发
        if (num < 1){
            return "forward:/supplier/sporderMsg";
        }

        //发货状态为true
//        orderService.send(no,true,true,2);
        orderService.send(no,true,2);
//        //调试
//         List<Order> orders = orderService.getOrders();
//        for (Order o:orders) {
//            System.out.println(o.toString());
//        }
        //商品库存-1
        productService.updateProductByName(orderByNo.getProName(),num-1);
        return  "redirect:/supplier/sporder";
//        return  "redirect:/supplier/sporder?refresh_catch";
    }

    /**
     * 库存不足时求请转发携带信息
     *
     * @param model
     * @return 库存不足信息
     */
    @GetMapping("/sporderMsg")
    public String sporder2(Model model){
        List<Order> orders = orderService.getOrders();
        model.addAttribute("orders",orders);
        model.addAttribute("msg","库存不足，无法发货！");
        return "supplier/sporder";
    }
}
