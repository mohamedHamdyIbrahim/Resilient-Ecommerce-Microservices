package org.example.orders.mapper;

import DTO.OrderDto;
import entities.Order;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);

    @AfterMapping
    default void linkOrdersItems(@MappingTarget Order order) {
        order.getOrdersItems().forEach(ordersItem -> ordersItem.setOrder(order));
    }

    @AfterMapping
    default void linkPayments(@MappingTarget Order order) {
        order.getPayments().forEach(payment -> payment.setOrder(order));
    }

    OrderDto toDto(Order order);

    List<OrderDto> toDto(List<Order> order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);
}