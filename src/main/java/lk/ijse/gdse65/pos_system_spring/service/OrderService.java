package lk.ijse.gdse65.pos_system_spring.service;

import lk.ijse.gdse65.pos_system_spring.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO saveOrder(OrderDTO order);
    List<OrderDTO> getAllOrder();
}
