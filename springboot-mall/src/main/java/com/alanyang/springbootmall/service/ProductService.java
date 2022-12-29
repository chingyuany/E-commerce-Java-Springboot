package com.alanyang.springbootmall.service;

import com.alanyang.springbootmall.dto.ProductRequest;
import com.alanyang.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
