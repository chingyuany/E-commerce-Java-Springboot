package com.alanyang.springbootmall.dao;

import com.alanyang.springbootmall.constant.ProductCategory;
import com.alanyang.springbootmall.dto.ProductQueryParams;
import com.alanyang.springbootmall.dto.ProductRequest;
import com.alanyang.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    Integer countProducts(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
