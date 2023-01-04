package com.alanyang.springbootmall.service;

import com.alanyang.springbootmall.dto.CreateOrderRequest;
import com.alanyang.springbootmall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId,CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
}
