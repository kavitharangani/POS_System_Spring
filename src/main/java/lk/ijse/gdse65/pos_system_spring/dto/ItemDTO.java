package lk.ijse.gdse65.pos_system_spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements SuperDTO{
    private String item_code;
    @NotNull(message = "Description cannot be null")
    private String description;
    @NotNull(message = "Qty cannot be null")
    private int qty;
    @NotNull(message = "Price cannot be null")
    private double unitPrice;
}
