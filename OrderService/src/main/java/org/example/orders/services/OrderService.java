package org.example.orders.services;

import entities.Order;
import entities.OrdersItem;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.example.amqp.RabbitMQMessageProducer;

import org.example.clients.PaymentClient;
import org.example.orders.mapper.OrderMapper;
import org.example.orders.repo.OrderItemsRepository;
import org.example.orders.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService implements OrderInterface {


    private OrderRepository orderRepository;
    private OrderItemsRepository orderItemsRepository;
    private PaymentClient paymentClient;
    private OrderMapper orderMapper;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
@Override
    public void addOrder(Order request) {
        request.setStatus("Pending");
        Order newOrder = orderRepository.saveAndFlush(request);
        for (OrdersItem ordersItem : request.getOrdersItems()) {
            ordersItem.setOrder(newOrder);
            orderItemsRepository.saveAndFlush(ordersItem);
        }
    placePayment(newOrder);
    }

    @Retry(name = "paymentRetry", fallbackMethod = "paymentFallback")
    public void placePayment(Order newOrder) {
        System.err.println("Calling payment");
    paymentClient.processPayment(newOrder.getId());
    }

    public String paymentFallback(Throwable throwable) {
        System.err.println("Payment service is currently unavailable. Please try again later.");
        return "Payment service is currently unavailable. Please try again later.";
    }
    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
    @Override
    public Order findByID(int id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.saveAndFlush(order);
    }

}
