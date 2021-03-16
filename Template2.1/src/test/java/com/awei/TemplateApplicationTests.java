package com.awei;

import com.awei.pojo.Product;
import com.awei.pojo.User;
import com.awei.service.impl.ProductServiceImpl;
import com.awei.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TemplateApplicationTests {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	ProductServiceImpl productService;

	@Test
	void contextLoads() {
		List<User> users = userService.getUsers();
		System.out.println(users);
	}

	@Test
	void searchLike() {
		List<Product> products = productService.getProductByLike("%包%");

		System.out.println("个数==>" + products.size());
		System.out.println(products);

	}
}
