package com.zebnitckii.twenty_first_century.test.service;

import com.zebnitckii.twenty_first_century.test.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order addNewOrder(Order order);

    Order updateOrder(Order order);

    Order getOrderById(long id);

    void deleteOrderById(long id);
}
