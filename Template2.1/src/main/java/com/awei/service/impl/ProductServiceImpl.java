package com.awei.service.impl;

import com.awei.dao.ProductMapper;
import com.awei.pojo.Product;
import com.awei.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper mapper;

    @Override
    public List<Product> getProducts() {
        return mapper.getProducts();
    }

    @Override
    public int addProduct(Product product) {
        return mapper.addProduct(product);
    }

    @Override
    public int deleteProduct(int no) {
        return mapper.deleteProduct(no);
    }

    @Override
    public Product getProductByNo(int no) {
        return mapper.getProductByNo(no);
    }

    @Override
    public int updateProduct(Product product) {
        return mapper.updateProduct(product);
    }

//    @Override
//    public int send(int no, boolean sendState, boolean isSend,int orderState) {
//        return mapper.send(no,sendState,isSend,orderState);
//    }

    @Override
    public List<Product> getProductBySearch(double money) {
        return mapper.getProductBySearch(money);
    }

    @Override
    public Product getProductBySearch2(String proName) {
        return mapper.getProductBySearch2(proName);
    }

    @Override
    public List<Product> getProductByType(int type) {
        return mapper.getProductByType(type);
    }

    @Override
    public int updateProductByName(String proName, int num) {
        return mapper.updateProductByName(proName,num);
    }

    @Override
    public List<Product> getProductByLike(String string) {
        return mapper.getProductByLike(string);
    }
}
