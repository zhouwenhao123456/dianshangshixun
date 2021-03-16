package com.awei.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    //订单编号
    private Integer no;
    //用户名
    private String username;
    //商品名
    private String proName;
    //收货地址
    private String addr;
    //总金额
    private double money;
    //订单状态 0:未受理 1:待发货 2:未签收 3:已签收
    private Integer orderState;
    //支付状态
    private boolean payState;
    //发货状态
    private boolean sendState;
    //商家是否接单
    private boolean isAccept;
}
