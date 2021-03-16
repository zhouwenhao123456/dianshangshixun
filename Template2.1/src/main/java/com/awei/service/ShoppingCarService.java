package com.awei.service;

import com.awei.pojo.ShoppingCar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShoppingCarService {
    /**
     * 向购物车中添加商品
     *
     * @param username 用户
     * @param proName 商品
     * @param money 金额
     * @return 添加的数量
     */
    int addProduct(String username, String proName, double money);

    /**
     * 查看购物车
     *
     * @param username 用户
     * @return 指定用户的购物车
     */
    List<ShoppingCar> getShoppingCars(String username);

    /**
     * 移除购物车商品
     *
     * @param id 购物车id
     * @return 移除的条数
     */
    int deleteProduct(int id);
    /**
     * 听过id查看购物车
     *
     * @param id
     * @return 购物车实体
     */
    ShoppingCar getProductById(int id);
}
