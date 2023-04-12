package com.zebnitckii.twenty_first_century.test.service;

import com.zebnitckii.twenty_first_century.test.entity.Goods;

import java.util.List;

public interface GoodsService {

    Goods addNewGoods(Goods goods);

    Goods updateGoods(Goods goods);

    void deleteGoodsById(long id);

    List<Goods> getAllGoods();

    Goods getGoodsById(long id);
}
