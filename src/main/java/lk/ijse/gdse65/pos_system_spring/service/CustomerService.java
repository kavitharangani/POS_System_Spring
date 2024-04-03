package lk.ijse.gdse65.pos_system_spring.service;

import lk.ijse.gdse65.pos_system_spring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customer);
    List<CustomerDTO> getAllCustomer();
    CustomerDTO getSelectedCustomer(String id);
    void deleteCustomer(String id);
    void updateCustomer(String id, CustomerDTO customer);
}
