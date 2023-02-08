package com.miniproject.orderservice.repository;

import com.miniproject.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long>{
}
