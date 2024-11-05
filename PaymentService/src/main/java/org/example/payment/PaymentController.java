package org.example.payment;

import DTO.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payment.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    private OrderMapper orderMapper;
    private final Random random = new Random();
    @Operation(
            summary = "Process Payment",
            description = "Endpoint that process a payment for an order and update order status according to payment status and send notification to massage queue (RabbitMQ)"
    )
    @PostMapping("/process-payment/{OrderID}")
    public String processPayment(@PathVariable("OrderID") Integer orderID) {
        if (random.nextInt(10) < 0) { // 70% chance to simulate failure
            paymentService.presistPayment(orderID,"Failed");
            throw new RuntimeException("Payment processing failed");

        }
        System.err.println("payment successful");
        paymentService.presistPayment(orderID,"Succeeded");
        return "Payment processed successfully";
    }
}