package com.alanyang.springbootmall.service;

import com.alanyang.springbootmall.dto.CreateOrderRequest;
import com.alanyang.springbootmall.dto.OrderQueryParams;
import com.alanyang.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId,CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Integer countOrder(OrderQueryParams orderQueryParams);
}
