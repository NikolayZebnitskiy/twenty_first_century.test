package com.zebnitckii.twenty_first_century.test.service.impl;

import com.zebnitckii.twenty_first_century.test.dao.OrderRepository;
import com.zebnitckii.twenty_first_century.test.entity.Goods;
import com.zebnitckii.twenty_first_century.test.entity.Order;
import com.zebnitckii.twenty_first_century.test.entity.OrderLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void getAllOrdersTest() {
        Order order1 = new Order(1L, "Ivan"
                , LocalDate.of(2003, 1, 3)
                ,"Rostov", Collections.emptyList());

        Order order2 = new Order(2L, "Lena"
                , LocalDate.of(2011, 5, 29)
                , "Moscow", Collections.emptyList());

        Mockito.when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orderList = orderService.getAllOrders();

        assertNotNull(orderList);
        assertEquals(2, orderList.size());
        assertEquals(1L, orderList.get(0).getId());
        assertEquals("Ivan", orderList.get(0).getClient());
        assertEquals("2003-01-03", orderList.get(0).getDate().toString());
        assertEquals("Rostov", orderList.get(0).getAddress());
        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void getOrderByIdTest() {
        Goods goods = new Goods(1L, "apple", 70.0);
        OrderLine orderLine = new OrderLine(1L, goods, 3);
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        Order order = new Order(1L, "Ivan"
                , LocalDate.of(2003, 1, 3)
                ,"Rostov", orderLines);

        Mockito.when(orderRepository.findOrderById(Mockito.anyLong())).thenReturn(Optional.of(order));

        Order receivedOrder = orderService.getOrderById(Mockito.anyLong());

        assertNotNull(receivedOrder);
        assertEquals(order.getId(), receivedOrder.getId());
        assertEquals(order.getClient(), receivedOrder.getClient());
        assertEquals(order.getOrderLines(), receivedOrder.getOrderLines());
        Mockito.verify(orderRepository, Mockito.times(1)).findOrderById(Mockito.anyLong());
    }

    @Test
    public void addNewOrderTest() {
        Order order = new Order(1L, "Ivan"
                , LocalDate.of(2003, 1, 3)
                ,"Rostov", Collections.emptyList());

        Mockito.when(orderRepository.save(order)).thenReturn(order);

        Order newOrder = orderService.addNewOrder(order);

        assertNotNull(newOrder);
        assertEquals(order.getId(), newOrder.getId());
        assertEquals(order.getClient(), newOrder.getClient());
        assertEquals(order.getDate(), newOrder.getDate());
        assertEquals(order.getOrderLines(), newOrder.getOrderLines());
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
    }

    @Test
    public void updateOrderTest() {
        Goods goods = new Goods(1L, "apple", 70.0);
        OrderLine orderLine = new OrderLine(1L, goods, 3);
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        Order order = new Order(1L, "Ivan"
                , LocalDate.of(2003, 1, 3)
                ,"Rostov", orderLines);

        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(order);

        Order updateOrder = orderService.updateOrder(order);

        assertNotNull(updateOrder);
        assertEquals(order.getId(), updateOrder.getId());
        assertEquals(order.getClient(), updateOrder.getClient());
        assertEquals(order.getDate(), order.getDate());
        assertEquals(order.getOrderLines(), updateOrder.getOrderLines());
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
    }

    @Test
    public void deleteOrderTest() {
        orderService.deleteOrderById(Mockito.anyLong());

        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}