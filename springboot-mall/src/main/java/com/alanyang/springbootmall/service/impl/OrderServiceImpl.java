package com.alanyang.springbootmall.service.impl;

import com.alanyang.springbootmall.dao.OrderDao;
import com.alanyang.springbootmall.dao.ProductDao;
import com.alanyang.springbootmall.dao.UserDao;
import com.alanyang.springbootmall.dto.BuyItem;
import com.alanyang.springbootmall.dto.CreateOrderRequest;
import com.alanyang.springbootmall.model.Order;
import com.alanyang.springbootmall.model.OrderItem;
import com.alanyang.springbootmall.model.Product;
import com.alanyang.springbootmall.model.User;
import com.alanyang.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    //     two database operation, so we need to use transaction
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
//        check user exist
        User user = userDao.getUserById(userId);
        if (user == null) {
            log.warn("user not exist, userId={}", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not exist");
        }
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            int productId = buyItem.getProductId();
            Product product = productDao.getProductById(productId);
//            check product exist and have enough stock
            if (product == null) {
                log.warn("product not exist, productId={}", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "product not exist");
            } else if (product.getStock() < buyItem.getQuantity()){
                log.warn("product stock not enough, productId={}, stock={}, to buy qty={}", buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "product stock not enough");
            }
//            update product stock
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

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
