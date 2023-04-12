package com.zebnitckii.twenty_first_century.test.service.impl;

import com.zebnitckii.twenty_first_century.test.dao.GoodsRepository;
import com.zebnitckii.twenty_first_century.test.entity.Goods;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GoodsServiceImplTest {

    @Mock
    private GoodsRepository goodsRepository;

    @InjectMocks
    private GoodsServiceImpl goodsService;

    @Test
    void addNewGoodsTest() {
        Goods goods = new Goods(1L, "apple", 15.0);

        Mockito.when(goodsRepository.save(goods)).thenReturn(goods);

        Goods newGoods = goodsService.addNewGoods(goods);

        assertNotNull(newGoods);
        assertEquals(goods.getId(), newGoods.getId());
        assertEquals(goods.getName(), newGoods.getName());
        assertEquals(goods.getPrice(), newGoods.getPrice());
        Mockito.verify(goodsRepository, Mockito.times(1)).save(goods);
    }

    @Test
    void updateGoodsTest() {
        Goods goods = new Goods(1L, "apple", 70.0);

        Mockito.when(goodsRepository.save(Mockito.any())).thenReturn(goods);

        Goods updateGoods = goodsService.updateGoods(goods);

        assertNotNull(goods);
        assertEquals(goods.getId(), updateGoods.getId());
        assertEquals(goods.getName(), updateGoods.getName());
        assertEquals(goods.getPrice(), updateGoods.getPrice());
        Mockito.verify(goodsRepository, Mockito.times(1)).save(goods);
    }

    @Test
    void getAllGoodsTest() {
        Goods goods1 = new Goods(1L, "apple", 70.0);
        Goods goods2 = new Goods(2L, "lemon", 20.5);

        Mockito.when(goodsRepository.findAll()).thenReturn(Arrays.asList(goods1, goods2));

        List<Goods> goodsList = goodsService.getAllGoods();

        assertNotNull(goodsList);
        assertEquals(2, goodsList.size());
        assertEquals(1L, goodsList.get(0).getId());
        assertEquals("apple", goodsList.get(0).getName());
        assertEquals(70.0, goodsList.get(0).getPrice());
        assertEquals(2L, goodsList.get(1).getId());
        Mockito.verify(goodsRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getGoodsByIdTest() {
        Goods goods = new Goods(1L, "apple", 70.0);

        Mockito.when(goodsRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(goods));

        Goods receivedGoods = goodsService.getGoodsById(Mockito.anyLong());

        assertNotNull(receivedGoods);
        assertEquals(goods.getId(), receivedGoods.getId());
        assertEquals(goods.getName(), receivedGoods.getName());
        assertEquals(goods.getPrice(), receivedGoods.getPrice());
        Mockito.verify(goodsRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void deleteGoodsByIdTest() {
        goodsService.deleteGoodsById(Mockito.anyLong());

        Mockito.verify(goodsRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}