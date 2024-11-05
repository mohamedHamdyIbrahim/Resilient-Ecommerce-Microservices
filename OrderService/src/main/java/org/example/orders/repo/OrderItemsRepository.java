package org.example.orders.repo;

import entities.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrdersItem,Integer> {
}
