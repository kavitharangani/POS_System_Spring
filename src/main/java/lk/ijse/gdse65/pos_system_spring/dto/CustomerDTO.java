package lk.ijse.gdse65.pos_system_spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements SuperDTO{
    private String customer_id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Address cannot be null")
    private String address;
    @NotNull(message = "Contact cannot be null")
    private String contact;
}
