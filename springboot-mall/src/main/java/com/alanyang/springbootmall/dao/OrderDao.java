package com.alanyang.springbootmall.dao;

import com.alanyang.springbootmall.dto.CreateOrderRequest;
import com.alanyang.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
