package lk.ijse.gdse65.pos_system_spring.service;

import lk.ijse.gdse65.pos_system_spring.dto.CustomerDTO;
import lk.ijse.gdse65.pos_system_spring.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO saveItem(ItemDTO item);
    List<ItemDTO> getAllItem();
    ItemDTO getSelectedItem(String id);
    void deleteItem(String id);
    void updateItem(String id, ItemDTO item);
}
