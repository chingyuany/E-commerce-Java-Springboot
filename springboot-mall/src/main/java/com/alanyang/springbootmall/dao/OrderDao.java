package com.alanyang.springbootmall.dao;

import com.alanyang.springbootmall.dto.CreateOrderRequest;
import com.alanyang.springbootmall.dto.OrderQueryParams;
import com.alanyang.springbootmall.model.Order;
import com.alanyang.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Integer createOrder(Integer userId, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
    Order getOrderById(Integer orderId);
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
}
