package com.awei.service.impl;

import com.awei.dao.OrderMapper;
import com.awei.pojo.Order;
import com.awei.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper mapper;

    @Override
    public List<Order> getOrders() {
        return mapper.getOrders();
    }

    @Override
    public int send(int no, boolean sendState,int orderState) {
        return mapper.send(no,sendState,orderState);
    }

    @Override
    public int deleteOrder(int no) {
        return mapper.deleteOrder(no);
    }

    @Override
    public int acceptOrder(int no, boolean isAccept, int orderState) {
        return mapper.acceptOrder(no,isAccept,orderState);
    }

    @Override
    public List<Order> getOrderByUsername(String username) {
        return mapper.getOrderByUsername(username);
    }

    @Override
    public int addOrder(Order order) {
        return mapper.addOrder(order);
    }

    @Override
    public Order getOrderByNo(int no) {
        return mapper.getOrderByNo(no);
    }

}
