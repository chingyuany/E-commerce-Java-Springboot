package com.alanyang.springbootmall.service;

import com.alanyang.springbootmall.dto.CreateOrderRequest;

public interface OrderService {
    Integer createOrder(Integer userId,CreateOrderRequest createOrderRequest);
}
