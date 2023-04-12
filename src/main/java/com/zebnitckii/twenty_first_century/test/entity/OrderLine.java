package com.zebnitckii.twenty_first_century.test.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @Column(name = "count")
    private int count;

    public OrderLine() {
    }

    public OrderLine(long id, Goods goods, int count) {
        this.id = id;
        this.goods = goods;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLine)) return false;
        OrderLine orderLine = (OrderLine) o;
        return id == orderLine.id && count == orderLine.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count);
    }

}
