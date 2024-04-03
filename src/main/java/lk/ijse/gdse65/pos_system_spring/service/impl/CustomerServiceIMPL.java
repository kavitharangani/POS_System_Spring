package lk.ijse.gdse65.pos_system_spring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse65.pos_system_spring.conversion.ConversionData;
import lk.ijse.gdse65.pos_system_spring.dto.CustomerDTO;
import lk.ijse.gdse65.pos_system_spring.entity.CustomerEntity;
import lk.ijse.gdse65.pos_system_spring.exception.NotFoundException;
import lk.ijse.gdse65.pos_system_spring.repository.CustomerDAO;
import lk.ijse.gdse65.pos_system_spring.service.CustomerService;
import lk.ijse.gdse65.pos_system_spring.util.UtilMatters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private ConversionData convert;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
//        customer.setCustomer_id(UtilMatters.generateId());
        return convert.convertToCustomerDTO(customerDAO.save(convert.convertToCustomerEntity(customer)));
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return convert.getCustomerDTOList(customerDAO.findAll());
    }

    @Override
    public CustomerDTO getSelectedCustomer(String id) {
        if (!customerDAO.existsById(id)) throw new NotFoundException("CUSTOMER NOT FOUND");
        return convert.convertToCustomerDTO(customerDAO.getReferenceById(id));
    }

    @Override
    public void deleteCustomer(String id) {
        if (!customerDAO.existsById(id)) throw new NotFoundException("CUSTOMER NOT FOUND");
        customerDAO.deleteById(id);
    }

    @Override
    public void updateCustomer(String id, CustomerDTO customer) {
        Optional<CustomerEntity> tmpCustomer = customerDAO.findById(id);
        if (!tmpCustomer.isPresent()) throw new NotFoundException("STUDENT NOT FOUND");
        tmpCustomer.get().setName(customer.getName());
        tmpCustomer.get().setAddress(customer.getAddress());
        tmpCustomer.get().setContact(customer.getContact());
    }
}
