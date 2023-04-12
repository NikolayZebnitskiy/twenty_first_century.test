package com.zebnitckii.twenty_first_century.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zebnitckii.twenty_first_century.test.entity.Goods;
import com.zebnitckii.twenty_first_century.test.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GoodsController.class)
class GoodsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GoodsService goodsService;

    @Test
    public void getAllGoodsTest() throws Exception
    {
        Goods goods1 = new Goods(1L, "apple", 15.0);
        Goods goods2 = new Goods(2L, "lemon", 20.2);

        Mockito.when(goodsService.getAllGoods()).thenReturn(Arrays.asList(goods1, goods2));

        mvc.perform(get("/goods/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(goods1, goods2))));
    }

    @Test
    public void getGoodsByIdTest() throws Exception {
        Goods goods = new Goods(1, "apple", 15.0);

        Mockito.when(goodsService.getGoodsById(Mockito.anyLong())).thenReturn(goods);

        mvc.perform(get("/goods/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("apple"))
                .andExpect(jsonPath("$.price").value("15.0"));
    }

    @Test
    public void addNewGoodsTest() throws Exception {
        Goods goods = new Goods(1L, "apple", 15.0);

        Mockito.when(goodsService.addNewGoods(Mockito.any())).thenReturn(goods);

        mvc.perform(post("/goods/create")
                        .content(objectMapper.writeValueAsString(goods))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(goods)));
    }

    @Test
    public void updateGoodsTest() throws Exception {
        Goods goods = new Goods(1L, "apple", 15.0);

        Mockito.when(goodsService.updateGoods(goods)).thenReturn(new Goods(1L, "apple", 18.2));
        Mockito.when(goodsService.getGoodsById(Mockito.anyLong())).thenReturn(goods);

        mvc.perform(put("/goods/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goods)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteGoodsByIdTest() throws Exception {
        Goods goods = new Goods(1L, "apple", 15.0);

        Mockito.when(goodsService.getGoodsById(Mockito.anyLong())).thenReturn(goods);

        mvc.perform(delete("/goods/1"))
                .andExpect(status().isNoContent());
    }

}