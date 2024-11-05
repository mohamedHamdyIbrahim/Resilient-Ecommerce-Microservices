package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link entities.OrdersItem}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersItemDto implements Serializable {
    ItemDto item;
    Integer quantity;
}