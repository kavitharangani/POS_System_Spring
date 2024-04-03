package lk.ijse.gdse65.pos_system_spring.conversion;

import lk.ijse.gdse65.pos_system_spring.dto.CustomerDTO;
import lk.ijse.gdse65.pos_system_spring.dto.ItemDTO;
import lk.ijse.gdse65.pos_system_spring.entity.CustomerEntity;
import lk.ijse.gdse65.pos_system_spring.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class  ConversionData {
    final private ModelMapper modelMapper;

    public ConversionData(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    // Customer
    public CustomerDTO convertToCustomerDTO(CustomerEntity customer){
        return modelMapper.map(customer,CustomerDTO.class);
    }
    public CustomerEntity convertToCustomerEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO,CustomerEntity.class);
    }
    public List<CustomerDTO> getCustomerDTOList(List<CustomerEntity> customerEntityList) {
        return modelMapper.map(customerEntityList,List.class);
    }


    // Item
    public ItemDTO convertToItemDTO(ItemEntity item){
        return modelMapper.map(item,ItemDTO.class);
    }
    public ItemEntity convertToItemEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO,ItemEntity.class);
    }
    public List<ItemDTO> getItemDTOList(List<ItemEntity> itemEntityList) {
        return modelMapper.map(itemEntityList,List.class);
    }
}
