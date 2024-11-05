package org.example.clients;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {
}
