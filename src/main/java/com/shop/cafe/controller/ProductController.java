package com.shop.cafe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Product;
import com.shop.cafe.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;
	
	Map<String, Object> storage = new HashMap<>();
	
	@CrossOrigin("http://127.0.0.1:5500")
	@GetMapping("getAllProducts")
	public List<Product> getAllProducts() {
		try {
			Object o = storage.get("firstPageProducts");
			if(o == null) {
				List<Product> list = productService.getAllProducts();
				storage.put("firstPageProducts", list);
				return list;
			}
			System.out.println(o.toString());
			return (List<Product>)o;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
