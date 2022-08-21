package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.business.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
