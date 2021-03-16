package com.awei.service;

import com.awei.pojo.Product;


import java.util.List;

public interface ProductService {
    /**
     * 查询所有商品
     *
     * @return 所有商品的集合
     */
    List<Product> getProducts();

    /**
     * 添加商品
     *
     * @param product 商品实体
     * @return 添加成功的条数
     */
    int addProduct(Product product);
    /**
     * 下架商品
     *
     * @param no 商品编号
     * @return 下架的条数
     */
    int deleteProduct(int no);
    /**
     * 通过编号获取指定商品
     *
     * @param no 商品编号
     * @return 当前编号商品
     */
    Product getProductByNo(int no);

    /**
     * 修改商品信息
     *
     * @param product 商品实体
     * @return 修改的条数
     */
    int updateProduct(Product product);

//    /**
//     * 发货
//     *
//     * @param no 订单编号
//     * @param sendState 发货状态
//     * @return 发货的数量
//     */
//    int send(int no,boolean sendState,boolean isSend,int orderState);

    /**
     * 通过价格查找
     *
     * @param money 价格
     * @return 符合条件的商品
     */
    List<Product> getProductBySearch(double money);

    /**
     * 通过商品名称查找
     *
     * @param proName 商品名称
     * @return 符合条件的商品
     */
    Product getProductBySearch2(String proName);

    /**
     * 通过类别查找
     *
     * @param type 类别
     * @return 此类别的所有商品
     */
    List<Product> getProductByType(int type);

    /**
     * 通过商品名称修改商品数量
     *
     * @param proName 商品名称
     * @param num 商品数量
     * @return 修改的商品条数
     */
    int updateProductByName(String proName,int num);

    /**
     * 通过名字进行模糊查询
     *
     * @param string 商品名称(模糊)
     * @return
     */
    List<Product> getProductByLike(String string);
}
