package com.zebnitckii.twenty_first_century.test.controller;

import com.zebnitckii.twenty_first_century.test.entity.Order;
import com.zebnitckii.twenty_first_century.test.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        for (Order order : orders) {
            order.setOrderLines(Collections.emptyList());
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
         Order order = orderService.getOrderById(id);

         if (order == null) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> addNewOrder(@RequestBody Order order) {
        orderService.addNewOrder(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order,
                                             @PathVariable("id") long id) {
        Order oldOrder = orderService.getOrderById(id);

        if (oldOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Order updateOrder = orderService.updateOrder(order);

        return new ResponseEntity<>(updateOrder,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable long id) {
        Order order = orderService.getOrderById(id);

        if (order == null) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        orderService.deleteOrderById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
