package com.zebnitckii.twenty_first_century.test.dao;

import com.zebnitckii.twenty_first_century.test.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o FROM Order o " +
            "LEFT JOIN FETCH o.orderLines WHERE o.id = :id")
    Optional<Order> findOrderById(long id);
    }
