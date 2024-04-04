package lk.ijse.gdse65.pos_system_spring.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse65.pos_system_spring.dto.ItemDTO;
import lk.ijse.gdse65.pos_system_spring.dto.OrderDTO;
import lk.ijse.gdse65.pos_system_spring.service.ItemService;
import lk.ijse.gdse65.pos_system_spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin
public class Order {
    @Autowired
    OrderService orderService;

    @GetMapping("/health")
    public String healthCheckItem(){
        return "Health Check Item";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public OrderDTO saveOrder(@Valid
                              @RequestPart("order_id")String order_id,
                              @RequestPart("customer_id")String customer_id,
                              @RequestPart("customer_name")String customer_name,
                              @RequestPart("order_item_id")String order_item_id,
                              @RequestPart ("description") String description,
                              @RequestPart ("total") String total,
                              Errors errors){
        if (errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }

        OrderDTO buildOrder = new OrderDTO();
        buildOrder.setOrder_id(order_id);
        buildOrder.setCustomer_id(customer_id);
        buildOrder.setCustomer_name(customer_name);
        buildOrder.setOrder_item_id(order_item_id);
        buildOrder.setDescription(description);
        buildOrder.setTotal(Double.parseDouble(total));

        return orderService.saveOrder(buildOrder);
    }
    @GetMapping(produces = "application/json")
    List<OrderDTO> getAllOrder(){
        return orderService.getAllOrder();
    }
}
