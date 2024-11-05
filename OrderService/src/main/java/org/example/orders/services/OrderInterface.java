package org.example.orders.services;

import entities.Order;

import java.util.List;

public interface OrderInterface {
    public void addOrder(Order request);

    public List<Order> findAllOrders();

    Order findByID(int id);

    void updateOrder(Order order);
}
