package org.example.notification;

import DTO.PaymentDto;
import entities.Notification;
import entities.Payment;
import lombok.AllArgsConstructor;
import org.example.notification.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class NotificationService {

    private NotificationRepo notificationRepo;
    private PaymentRepository  paymentRepository;
    private PaymentMapper paymentMapper;
    public void sendNotification(int paymentID) {
        Payment payment = paymentRepository.findById(paymentID).get();
        System.err.println("Order "+payment.getOrder());
        System.err.println("User "+payment.getOrder().getUser());
        Notification notification =  Notification.builder()
                .user(payment.getOrder().getUser())
                .payment(payment)
                .message("our payment is "+payment.getPaymentStatus())
                .build();
        notificationRepo.saveAndFlush(notification);


    }


}
