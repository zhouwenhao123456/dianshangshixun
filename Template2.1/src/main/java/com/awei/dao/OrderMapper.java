package com.awei.dao;

import com.awei.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    /**
     * 获取全部订单
     *
     * @return
     */
    List<Order> getOrders();

    /**
     * 拒绝订单
     *
     * @param no 要拒绝订单的编号
     * @return 拒绝点单的个数
     */
    int deleteOrder(@Param("no") int no);

    /**
     * 接受订单
     *
     * @param no 订单编号
     * @param isAccept 是否接单
     * @param orderState 订单状态
     * @return 受理的条数
     */
    int acceptOrder(@Param("no") int no,@Param("isAccept") boolean isAccept,@Param("orderState") int orderState);
    /**
     * 发货
     *
     * @param no 订单编号
     * @param sendState 发货状态
     * @return 发货的数量
     */
    int send(@Param("no") int no, @Param("sendState") boolean sendState,@Param("orderState")int orderState );


    /**
     * 通过用户名查询订单
     *
     * @param username 用户名
     * @return 当前用户订单
     */
    List<Order> getOrderByUsername(@Param("username") String username);

    /**
     * 下单
     *
     * @param order 订单实体
     * @return 下单的条数
     */
    int addOrder(Order order);

    /**
     * 通过id查找订单
     *
     * @param no 订单no
     * @return 订单
     */
    Order getOrderByNo(@Param("no") int no);
}
