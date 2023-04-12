package com.zebnitckii.twenty_first_century.test.service.impl;

import com.zebnitckii.twenty_first_century.test.dao.OrderRepository;
import com.zebnitckii.twenty_first_century.test.entity.Order;
import com.zebnitckii.twenty_first_century.test.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order addNewOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findOrderById(id).orElse(null);
    }

    @Override
    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }

}
