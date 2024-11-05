package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ITEMS")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 100)
    @jakarta.validation.constraints.NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;

    @jakarta.validation.constraints.NotNull
    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "CREATED_AT")
    private Instant createdAt;

}