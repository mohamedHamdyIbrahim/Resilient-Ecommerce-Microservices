package org.example.orders.controller;

import DTO.OrderDto;
import entities.Order;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orders.mapper.OrderMapper;
import org.example.orders.services.OrderInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class OrderController {

    private OrderMapper orderMapper;

    private OrderInterface orderService;


    @Operation(
            summary = "Create Order",
            description = "Creating an order which include selected items"
    )
    @PostMapping("/order")
    public void registerOrder(@RequestBody OrderDto orderRequest) {
        log.info("new customer request {}", orderRequest);
        orderService.addOrder(orderMapper.toEntity(orderRequest));
    }
    @Operation(
            summary = "Update Order",
            description = "update specific order"
    )
    @PostMapping("/updateOrder")
    public void updateOrder(@RequestBody OrderDto order) {
        log.info("new customer request {}", order);
        orderService.updateOrder(orderMapper.toEntity(order) );
    }
    @Operation(
            summary = "Fetch All Orders",
            description = "get all orders in database"
    )
    @GetMapping("/orders")
    public List<OrderDto> getAllOrders() {

        List<Order> orders = orderService.findAllOrders();

       return  orderMapper.toDto(orders);

    }
    @Operation(
            summary = "Fetch Order By ID",
            description = "Fetch specific Order"
    )
    @GetMapping("/orders/{OrderID}")
    public OrderDto getOrderByID (@PathVariable("OrderID") int OrderID) {
        Order order = orderService.findByID(OrderID);
       return  orderMapper.toDto(order);

    }
}
