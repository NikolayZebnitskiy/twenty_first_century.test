package com.zebnitckii.twenty_first_century.test.dao;

import com.zebnitckii.twenty_first_century.test.entity.Goods;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GoodsRepositoryTest {

    @Autowired
    GoodsRepository goodsRepository;

    @Test
    @Sql("/sql/updateDB.sql")
    public void getAllGoodsTest() {
        List<Goods> goodsList = goodsRepository.findAll();

        assertNotNull(goodsList);
        assertEquals(3, goodsList.size());
        assertEquals("apple", goodsList.get(0).getName());
        assertEquals(70.0, goodsList.get(0).getPrice());
        assertThrows(LazyInitializationException.class, () -> {goodsList.get(0).getOrderLines().get(0);});
    }

    @Test
    public void getGoodsByIdTest() {
        Goods goods = goodsRepository.findById(1L).orElse(null);

        assertNotNull(goods);
        assertEquals(1L, goods.getId());
        assertEquals("apple", goods.getName());
        assertEquals(70.0, goods.getPrice());
        assertThrows(LazyInitializationException.class, () -> {goods.getOrderLines().get(0);});
    }

    @Test
    public void addNewGoodsTest() {
        Goods goods = new Goods();
        goods.setName("lemon");
        goods.setPrice(15.7);

        Goods saveGoods = goodsRepository.save(goods);

        Goods newGoods = goodsRepository.findById(4L).orElse(null);

        assertNotNull(saveGoods);
        assertNotNull(newGoods);
        assertEquals(4L, saveGoods.getId());
        assertEquals(saveGoods, newGoods);
    }

    @Test
    public void updateGoodsTest() {
        Goods goods = goodsRepository.findById(2L).orElse(null);

        assert goods != null;
        goods.setPrice(30.0);
        goods.setName("pear");

        goodsRepository.save(goods);

        Goods updateGoods = goodsRepository.findById(2L).orElse(null);

        assertNotNull(updateGoods);
        assertEquals(goods.getPrice(), updateGoods.getPrice());
        assertEquals(goods.getName(), updateGoods.getName());
    }

    @Test
    public void deleteGoodsByIdTest() {
        Goods goods = goodsRepository.findById(3L).orElse(null);

        assert goods != null;
        goodsRepository.deleteById(3L);

        Goods deletedGoods = goodsRepository.findById(3L).orElse(null);

        assertNull(deletedGoods);
    }
}
