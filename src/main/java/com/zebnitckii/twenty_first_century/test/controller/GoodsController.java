package com.zebnitckii.twenty_first_century.test.controller;

import com.zebnitckii.twenty_first_century.test.entity.Goods;
import com.zebnitckii.twenty_first_century.test.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/all")
    public ResponseEntity<List<Goods>> getAllGoods() {
        List<Goods> goods = goodsService.getAllGoods();
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goods> getGoodsById(@PathVariable long id) {
        Goods goods = goodsService.getGoodsById(id);

        if (goods == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Goods> addNewGoods(@RequestBody Goods goods) {
        goodsService.addNewGoods(goods);
        return new ResponseEntity<>(goods, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Goods> updateGoods(@RequestBody Goods goods,
                                             @PathVariable("id") long id) {
        Goods oldGoods = goodsService.getGoodsById(id);

        if (oldGoods == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Goods updateGoods = goodsService.updateGoods(goods);

        return new ResponseEntity<>(updateGoods, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoodsById(@PathVariable long id) {
        Goods goods = goodsService.getGoodsById(id);

        if (goods == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        goodsService.deleteGoodsById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
