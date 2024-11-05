package entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "USER_ID", nullable = false)
    private Euser user;

    @Column(name = "ORDER_DATE")
    private Instant orderDate;

    @jakarta.validation.constraints.Size(max = 20)
    @Column(name = "STATUS", length = 20)
    private String status;



    @OneToMany(mappedBy = "order")
    private Set<OrdersItem> ordersItems = new LinkedHashSet<>();

    @OneToMany(mappedBy = "order")
    private Set<Payment> payments = new LinkedHashSet<>();

}