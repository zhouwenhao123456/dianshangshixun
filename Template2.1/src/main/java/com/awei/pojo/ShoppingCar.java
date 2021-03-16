package com.awei.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCar implements Serializable {
    //id
    private Integer id;
    //用户
    private String username;
    //商品名称
    private String proName;
    //金额
    private double money;
}
