package org.example.payment;

import entities.Order;
import entities.OrdersItem;
import entities.Payment;
import lombok.AllArgsConstructor;
import org.example.amqp.RabbitMQMessageProducer;
import org.example.clients.NotificationRequest;
import org.example.clients.OrderClient;
import org.example.payment.mapper.OrderMapper;
import org.example.payment.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository   orderRepository;
    private OrderMapper orderMapper;
    private PaymentMapper  paymentMapper;
    private OrderClient orderClient;
    private RabbitMQMessageProducer rabbitMQMessageProducer;

    public void presistPayment(Integer orderID, String status) {

        Order order =orderRepository.findById(orderID).get();
        double value=0;
        for (OrdersItem ordersItem:order.getOrdersItems()) {
            value+=(ordersItem.getItem().getPrice().doubleValue() * ordersItem.getQuantity());
        }
        Payment  newPaymnent = Payment.builder()
                .paymentStatus(status)
                .order(order)
                .amount(BigDecimal.valueOf(value))
                .paymentDate(Instant.now())
                .paymentMethod("Visa")
                .build();

        newPaymnent = paymentRepository.saveAndFlush(newPaymnent);
        if (status.equals("failed")) {
            order.setStatus("Payment rejected");
        }
        else
        {
            order.setStatus("Order Confirmed");
        }
        orderClient.updateOrder( orderMapper.toDto(order));

        rabbitMQMessageProducer.publish(newPaymnent.getId() ,  "internal.exchange",
                "internal.notification.routing-key");
    }

}