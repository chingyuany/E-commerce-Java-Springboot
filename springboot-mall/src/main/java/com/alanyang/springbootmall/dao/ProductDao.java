package com.alanyang.springbootmall.dao;

import com.alanyang.springbootmall.dto.ProductRequest;
import com.alanyang.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
