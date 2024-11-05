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

/**
 * DTO for {@link entities.Item}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 100)
    String name;
    @Size(max = 255)
    String description;
    @NotNull
    BigDecimal price;
    Integer stock;
    Instant createdAt;
}