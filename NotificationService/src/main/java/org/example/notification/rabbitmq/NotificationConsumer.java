package org.example.notification.rabbitmq;

import DTO.PaymentDto;
import entities.Order;
import entities.Payment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.NotificationRequest;
import org.example.notification.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(int paymentID) {
        log.info("Consumed {} from queue", paymentID);
        notificationService.sendNotification(paymentID);
    }
}