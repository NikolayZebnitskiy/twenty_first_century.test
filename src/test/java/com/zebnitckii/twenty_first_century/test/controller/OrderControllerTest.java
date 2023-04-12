package com.zebnitckii.twenty_first_century.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zebnitckii.twenty_first_century.test.entity.Goods;
import com.zebnitckii.twenty_first_century.test.entity.Order;
import com.zebnitckii.twenty_first_century.test.entity.OrderLine;
import com.zebnitckii.twenty_first_century.test.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @Test
    public void getAllOrdersTest() throws Exception
    {
        Order order1 = new Order(1L, "Ivan"
                , LocalDate.of(2003, 1, 3)
                ,"Rostov", Collections.emptyList());

        Order order2 = new Order(2L, "Lena"
                , LocalDate.of(2011, 5, 29)
                , "Moscow", Collections.emptyList());

        Mockito.when(orderService.getAllOrders()).thenReturn(Arrays.asList(order1, order2));

        mvc.perform(get("/orders/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(order1, order2))));
    }

    @Test
    public void getOrderByIdTest() throws Exception {
        Goods goods = new Goods(1L, "apple", 70.0);
        OrderLine orderLine = new OrderLine(1L, goods, 3);
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        Order order = new Order(1L, "Ivan"
                , LocalDate.of(2003, 1, 3)
                ,"Rostov", orderLines);

        Mockito.when(orderService.getOrderById(Mockito.anyLong())).thenReturn(order);

        mvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.client").value("Ivan"))
                .andExpect(jsonPath("$.date").value("2003-01-03"))
                .andExpect(jsonPath("$.address").value("Rostov"))
                .andExpect(jsonPath("$.orderLines[0].id").value("1"))
                .andExpect(jsonPath("$.orderLines[0].goods.id").value("1"))
                .andExpect(jsonPath("$.orderLines[0].goods.name").value("apple"))
                .andExpect(jsonPath("$.orderLines[0].goods.price").value("70.0"))
                .andExpect(jsonPath("$.orderLines[0].count").value("3"));
    }

    @Test
    public void addNewOrderTest() throws Exception {
        Order order = new Order(1L, "Ivan"
                , LocalDate.of(2003, 1, 3)
                ,"Rostov", Collections.emptyList());

        Mockito.when(orderService.addNewOrder(Mockito.any())).thenReturn(order);

        mvc.perform(post("/orders/create")
                .content(objectMapper.writeValueAsString(order))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(order)));
    }

    @Test
    public void updateOrderTest() throws Exception {
        Goods goods = new Goods(1L, "apple", 70.0);
        OrderLine orderLine = new OrderLine(1L, goods, 3);
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        Order order = new Order(1L, "Ivan"
                , LocalDate.of(2003, 1, 3)
                ,"Rostov", orderLines);

        Mockito.when(orderService.updateOrder(order)).thenReturn(new Order(1L, "Dima"
                , LocalDate.of(2003, 1, 3)
                , "Rostov", Collections.emptyList()));
        Mockito.when(orderService.getOrderById(Mockito.anyLong())).thenReturn(order);

        mvc.perform(put("/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteOrderByIdTest() throws Exception {
        Order order = new Order(1L, "Ivan"
                , LocalDate.of(2003, 1, 3)
                ,"Rostov", Collections.emptyList());

        Mockito.when(orderService.getOrderById(Mockito.anyLong())).thenReturn(order);

        mvc.perform(delete("/orders/1"))
                .andExpect(status().isNoContent());
    }
}