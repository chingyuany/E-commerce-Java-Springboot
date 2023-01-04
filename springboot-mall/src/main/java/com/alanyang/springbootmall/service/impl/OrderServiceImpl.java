package com.alanyang.springbootmall.service.impl;

import com.alanyang.springbootmall.dao.OrderDao;
import com.alanyang.springbootmall.dao.ProductDao;
import com.alanyang.springbootmall.dto.BuyItem;
import com.alanyang.springbootmall.dto.CreateOrderRequest;
import com.alanyang.springbootmall.model.Order;
import com.alanyang.springbootmall.model.OrderItem;
import com.alanyang.springbootmall.model.Product;
import com.alanyang.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;


    //     two database operation, so we need to use transaction
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            int productId = buyItem.getProductId();
            Product product = productDao.getProductById(productId);
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;

//          convert BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);

        }
        Integer orderId = orderDao.createOrder(userId, totalAmount);
        orderDao.createOrderItems(orderId, orderItemList);
        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);
        order.setOrderItemList(orderItemList);
        return order;
    }
}
