package DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link entities.Payment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto implements Serializable {
    Integer id;
    @NotNull
    BigDecimal amount;
    Instant paymentDate;
    @Size(max = 20)
    String paymentStatus;
    @Size(max = 50)
    String paymentMethod;

}