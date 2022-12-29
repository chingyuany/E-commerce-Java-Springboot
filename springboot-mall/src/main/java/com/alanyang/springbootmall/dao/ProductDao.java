package com.alanyang.springbootmall.dao;

import com.alanyang.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
