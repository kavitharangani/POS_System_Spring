package lk.ijse.gdse65.pos_system_spring.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse65.pos_system_spring.dto.CustomerDTO;
import lk.ijse.gdse65.pos_system_spring.service.CustomerService;
import lk.ijse.gdse65.pos_system_spring.util.UtilMatters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class Customer {
    @Autowired
    CustomerService customerService;

    @GetMapping("/health")
    public String healthCheckStudent(){
        return "Health Check Customer";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CustomerDTO saveCustomer(@Valid
                                    @RequestPart("customer_id")String customer_id,
                                  @RequestPart ("name") String name,
                                   @RequestPart ("address") String address,
                                   @RequestPart ("contact") String contact,
                                   Errors errors){
        if (errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }

        CustomerDTO buildCustomer = new CustomerDTO();
        buildCustomer.setCustomer_id(customer_id);
        buildCustomer.setName(name);
        buildCustomer.setAddress(address);
        buildCustomer.setContact(contact);

        return customerService.saveCustomer(buildCustomer);
    }

    @GetMapping(value = "/{customer_id}",produces = "application/json")
    ResponseEntity<CustomerDTO> getSelectedCustomer(@PathVariable ("customer_id") String id){
        CustomerDTO seletedCustomer = customerService.getSelectedCustomer(id);
        return seletedCustomer != null ? ResponseEntity.ok(seletedCustomer) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(produces = "application/json")
    List<CustomerDTO> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{customer_id}")
    public void deleteCustomer(@PathVariable ("customer_id") String id){
        customerService.deleteCustomer(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateCustomer(@Valid
                              @RequestPart ("name") String name,
                              @RequestPart ("address") String address,
                              @RequestPart ("contact") String contact,
                              @RequestParam ("customer_id") String id,
                              Errors errors){
        if(errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }
        //Build Base64 image
//        String updatedBase64ProPic = UtilMatters.convertBase64(profilePic);
        //build object
        CustomerDTO updatedBuildCustomer = new CustomerDTO();
        updatedBuildCustomer.setName(name);
        updatedBuildCustomer.setAddress(address);
        updatedBuildCustomer.setContact(contact);

        customerService.updateCustomer(id,updatedBuildCustomer);
    }
}
