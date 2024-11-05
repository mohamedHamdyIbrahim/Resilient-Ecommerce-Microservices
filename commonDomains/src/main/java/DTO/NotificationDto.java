package DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link entities.Notification}
 */
@Value
public class NotificationDto implements Serializable {
    Integer id;
    @NotNull
    PaymentDto payment;
    @NotNull
    EuserDto user;
    Instant notificationDate;
    @Size(max = 1000000000)
    String message;
    @Size(max = 20)
    String status;
}