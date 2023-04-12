package com.zebnitckii.twenty_first_century.test.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "client")
    private String client;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "address")
    private String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderLine> orderLines;

    public Order() {
    }

    public Order(long id, String client, LocalDate date, String address, List<OrderLine> orderLines) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.address = address;
        this.orderLines = orderLines;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(client, order.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", date=" + date +
                ", address='" + address + '\'' +
                '}';
    }
}
