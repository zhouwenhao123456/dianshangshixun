package com.awei.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable{
    //商品编号
    private Integer no;
    //商品名称
    private String proName;
    //商品描述
    private String proInfo;
    //商品金额
    private double money;
    //商品数量
    private Integer num;
    //分类 0:汽车  1:手机  2:坚果  3:手表  4:老白干
    private Integer type;
}
