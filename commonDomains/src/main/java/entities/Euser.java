package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "EUSERS")
public class Euser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @jakarta.validation.constraints.Size(max = 100)
    @jakarta.validation.constraints.NotNull
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @jakarta.validation.constraints.Size(max = 100)
    @jakarta.validation.constraints.NotNull
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Column(name = "CREATED_AT")
    private Instant createdAt;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new LinkedHashSet<>();

}