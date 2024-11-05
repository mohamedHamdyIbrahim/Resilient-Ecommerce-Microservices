package entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PAYMENT")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_ID", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @jakarta.validation.constraints.NotNull
    @Column(name = "AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "PAYMENT_DATE")
    private Instant paymentDate;

    @jakarta.validation.constraints.Size(max = 20)
    @Column(name = "PAYMENT_STATUS", length = 20)
    private String paymentStatus;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "PAYMENT_METHOD", length = 50)
    private String paymentMethod;

    @OneToMany(mappedBy = "payment")
    private Set<Notification> notifications = new LinkedHashSet<>();

}