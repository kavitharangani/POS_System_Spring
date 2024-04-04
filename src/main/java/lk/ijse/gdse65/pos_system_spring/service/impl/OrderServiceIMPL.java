package lk.ijse.gdse65.pos_system_spring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse65.pos_system_spring.conversion.ConversionData;
import lk.ijse.gdse65.pos_system_spring.dto.OrderDTO;
import lk.ijse.gdse65.pos_system_spring.repository.CustomerDAO;
import lk.ijse.gdse65.pos_system_spring.repository.OrderDAO;
import lk.ijse.gdse65.pos_system_spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ConversionData convert;
    @Override
    public OrderDTO saveOrder(OrderDTO order) {
        return convert.convertToOrderDTO(orderDAO.save(convert.convertToOrderEntity(order)));

    }
    @Override
    public List<OrderDTO> getAllOrder() {
        return convert.getOrderDTOList(orderDAO.findAll());
    }
}
