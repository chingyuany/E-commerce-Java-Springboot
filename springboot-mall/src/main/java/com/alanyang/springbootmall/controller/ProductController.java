package com.alanyang.springbootmall.controller;

import com.alanyang.springbootmall.dto.ProductRequest;
import com.alanyang.springbootmall.model.Product;
import com.alanyang.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
       List<Product> productList = productService.getProducts();
       return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);
        if (product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
       Integer productId = productService.createProduct(productRequest);
       Product product = productService.getProductById(productId);
       return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody @Valid ProductRequest productRequest){
//        check if product exist
        Product product = productService.getProductById(productId);
        if (product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

//        update product info
        productService.updateProduct(productId, productRequest);
        Product updatedProduct  = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
