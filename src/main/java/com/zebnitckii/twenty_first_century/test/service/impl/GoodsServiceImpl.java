package com.zebnitckii.twenty_first_century.test.service.impl;

import com.zebnitckii.twenty_first_century.test.dao.GoodsRepository;
import com.zebnitckii.twenty_first_century.test.entity.Goods;
import com.zebnitckii.twenty_first_century.test.service.GoodsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Goods addNewGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public Goods updateGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public void deleteGoodsById(long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods getGoodsById(long id) {
        return goodsRepository.findById(id).orElse(null);
    }
}
