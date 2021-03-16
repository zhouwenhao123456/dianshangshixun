package com.awei.dao;

import com.awei.pojo.Product;
import com.awei.pojo.ShoppingCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShoppingCarMapper {
    /**
     * 向购物车中添加商品
     *
     * @param username 用户
     * @param proName 商品
     * @param money 金额
     * @return 添加的数量
     */
    int addProduct(@Param("username")String username,@Param("proName")String proName,@Param("money")double money);

    /**
     * 查看购物车
     *
     * @param username 用户
     * @return 指定用户的购物车
     */
    List<ShoppingCar> getShoppingCars(@Param("username") String username);

    /**
     * 移除购物车商品
     *
     * @param id 购物车id
     * @return 移除的条数
     */
    int deleteProduct(@Param("id")int id);

    /**
     * 听过id查看购物车
     *
     * @param id
     * @return 购物车实体
     */
    ShoppingCar getProductById(@Param("id")int id);

}
