package lk.ijse.gdse65.pos_system_spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO implements SuperDTO {
    private String order_id;
    @NotNull(message = "Customer id cannot be null")
    private String customer_id;
    @NotNull(message = "Name cannot be null")
    private String customer_name;
    @NotNull(message = "Order id cannot be null")
    private String order_item_id;
    @NotNull(message = "Description cannot be null")
    private String description;
    @NotNull(message = "Total cannot be null")
    private double total;
}
