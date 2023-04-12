package com.zebnitckii.twenty_first_century.test.dao;

import com.zebnitckii.twenty_first_century.test.entity.Goods;
import com.zebnitckii.twenty_first_century.test.entity.Order;
import com.zebnitckii.twenty_first_century.test.entity.OrderLine;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Sql("/sql/updateDB.sql")
    public void getAllOrdersTest() {
        List<Order> orderList = orderRepository.findAll();

        assertNotNull(orderList);
        assertEquals(5, orderList.size());
        assertEquals("Ivan", orderList.get(0).getClient());
        assertEquals("Moscow", orderList.get(1).getAddress());
        assertThrows(LazyInitializationException.class, () -> {orderList.get(0).getOrderLines().get(0);});
    }

    @Test
    public void getOrderByIdTest() {
        Order order = orderRepository.findOrderById(1L).orElse(null);

        assertNotNull(order);
        assertEquals(1L, order.getId());
        assertEquals("Ivan", order.getClient());
        assertEquals("2003-01-03", order.getDate().toString());
        assertEquals("Rostov", order.getAddress());
        assertEquals(5, order.getOrderLines().get(0).getCount());
        assertEquals("apple", order.getOrderLines().get(0).getGoods().getName());
        assertEquals(70.0, order.getOrderLines().get(0).getGoods().getPrice());
    }

    @Test
    public void addNewOrderTest() {
        Goods goods = new Goods(1L, "apple", 70.0);
        OrderLine orderLine = new OrderLine();
        orderLine.setGoods(goods);
        orderLine.setCount(50);
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        Order order = new Order();
        order.setClient("Masha");
        order.setDate(LocalDate.of(2023, 3, 7));
        order.setAddress("Minsk");
        order.setOrderLines(orderLines);

        Order saveOrder = orderRepository.save(order);

        Order newOrder = orderRepository.findOrderById(6L).orElse(null);

        assertNotNull(saveOrder);
        assertNotNull(newOrder);
        assertEquals(6L, saveOrder.getId());
        assertEquals(saveOrder, newOrder);
    }

    @Test
    public void updateOrderTest() {
        Order order = orderRepository.findOrderById(2L).orElse(null);

        assert order != null;
        order.setAddress("Rostov");
        order.getOrderLines().get(0).setCount(30);

        orderRepository.save(order);

        Order updateOrder = orderRepository.findOrderById(2L).orElse(null);

        assertNotNull(updateOrder);
        assertEquals(order.getAddress(), updateOrder.getAddress());
        assertEquals(order.getOrderLines().get(0).getCount(), updateOrder.getOrderLines().get(0).getCount());
    }

    @Test
    public void deleteOrderByIdTest() {
        Order order = orderRepository.findOrderById(4L).orElse(null);

        assert order != null;
        orderRepository.deleteById(order.getId());

        Order deletedOrder = orderRepository.findOrderById(4L).orElse(null);

        assertNull(deletedOrder);
    }
}
