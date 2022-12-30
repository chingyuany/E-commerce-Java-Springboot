package com.alanyang.springbootmall.dto;

import com.alanyang.springbootmall.constant.ProductCategory;
import org.springframework.web.bind.annotation.RequestParam;

public class ProductQueryParams {
    ProductCategory category;
    String search;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
