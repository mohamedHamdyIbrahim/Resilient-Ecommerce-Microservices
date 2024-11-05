package DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link entities.Euser}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EuserDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 50)
    String username;
    @NotNull
    @Size(max = 100)
    String email;
    @NotNull
    @Size(max = 100)
    String password;
    Instant createdAt;
}