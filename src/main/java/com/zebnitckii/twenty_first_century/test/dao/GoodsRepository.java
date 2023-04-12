package com.zebnitckii.twenty_first_century.test.dao;

import com.zebnitckii.twenty_first_century.test.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
