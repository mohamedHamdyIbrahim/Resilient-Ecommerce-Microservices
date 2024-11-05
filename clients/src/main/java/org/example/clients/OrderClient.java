package org.example.clients;

import DTO.OrderDto;
import entities.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient("order")
public interface OrderClient {

    @PostMapping("api/v1/updateOrder")
    public void updateOrder(@RequestBody OrderDto order);
}
