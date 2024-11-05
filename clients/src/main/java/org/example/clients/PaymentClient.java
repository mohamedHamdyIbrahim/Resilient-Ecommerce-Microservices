package org.example.clients;

import DTO.OrderDto;
import entities.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("payment")
public interface PaymentClient {
    @PostMapping("api/v1/process-payment/{OrderID}")
    String processPayment(@PathVariable("OrderID") Integer orderID) ;
}
