package com.alanyang.springbootmall.service;

import com.alanyang.springbootmall.dto.ProductQueryParams;
import com.alanyang.springbootmall.dto.ProductRequest;
import com.alanyang.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
