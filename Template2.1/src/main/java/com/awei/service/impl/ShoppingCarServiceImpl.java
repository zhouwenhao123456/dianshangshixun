package com.awei.service.impl;

import com.awei.dao.ShoppingCarMapper;
import com.awei.pojo.ShoppingCar;
import com.awei.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {

    @Autowired
    ShoppingCarMapper mapper;

    @Override
    public int addProduct(String username, String proName, double money) {
        return mapper.addProduct(username,proName,money);
    }

    @Override
    public List<ShoppingCar> getShoppingCars(String username) {
        return mapper.getShoppingCars(username);
    }

    @Override
    public int deleteProduct(int id) {
        return mapper.deleteProduct(id);
    }

    @Override
    public ShoppingCar getProductById(int id) {
        return mapper.getProductById(id);
    }
}
