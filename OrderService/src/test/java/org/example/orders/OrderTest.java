package org.example.orders;

import entities.Order;
import entities.OrdersItem;
import org.example.amqp.RabbitMQMessageProducer;
import org.example.clients.PaymentClient;
import org.example.orders.repo.OrderItemsRepository;
import org.example.orders.repo.OrderRepository;
import org.example.orders.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemsRepository orderItemsRepository;

    @Mock
    private PaymentClient paymentClient;

    @Mock
    private RabbitMQMessageProducer rabbitMQMessageProducer;

    @InjectMocks
    private OrderService orderService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrder_PaymentServiceFallback() {
        // Arrange
        Order order = new Order();
        order.setStatus("Pending");
        OrdersItem orderItem = new OrdersItem();
        order.setOrdersItems(new HashSet<>(Collections.singleton(orderItem)));

        // Mock saving order and item
        when(orderRepository.saveAndFlush(any(Order.class))).thenReturn(order);
        when(orderItemsRepository.saveAndFlush(any(OrdersItem.class))).thenReturn(orderItem);

        // Mock the payment client to throw an exception
        doThrow(RuntimeException.class).when(paymentClient).processPayment(anyInt());

        // Act
        assertDoesNotThrow(() -> orderService.addOrder(order));

        // Verify interactions
        verify(orderRepository, times(1)).saveAndFlush(order);
        verify(orderItemsRepository, times(1)).saveAndFlush(orderItem);
        verify(paymentClient, times(1)).processPayment(order.getId());
    }


}
