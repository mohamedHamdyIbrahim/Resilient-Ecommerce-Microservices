package org.example.payment.mapper;

import DTO.PaymentDto;
import entities.Payment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    Payment toEntity(PaymentDto paymentDto);

    @AfterMapping
    default void linkNotifications(@MappingTarget Payment payment) {
        payment.getNotifications().forEach(notification -> notification.setPayment(payment));
    }

    PaymentDto toDto(Payment payment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Payment partialUpdate(PaymentDto paymentDto, @MappingTarget Payment payment);
}