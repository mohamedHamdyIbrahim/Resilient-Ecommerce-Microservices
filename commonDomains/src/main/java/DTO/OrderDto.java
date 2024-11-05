package DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link entities.Order}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    Integer id;
    @NotNull
    EuserDto user;
    Instant orderDate;
    @Size(max = 20)
    String status;
    Set<OrdersItemDto> ordersItems;
    Set<PaymentDto> payments;
}