package lk.ijse.gdse65.pos_system_spring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse65.pos_system_spring.conversion.ConversionData;
import lk.ijse.gdse65.pos_system_spring.dto.ItemDTO;
import lk.ijse.gdse65.pos_system_spring.entity.ItemEntity;
import lk.ijse.gdse65.pos_system_spring.exception.NotFoundException;
import lk.ijse.gdse65.pos_system_spring.repository.CustomerDAO;
import lk.ijse.gdse65.pos_system_spring.repository.ItemDAO;
import lk.ijse.gdse65.pos_system_spring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemDAO itemDao;
    @Autowired
    private ConversionData convert;
    @Override
    public ItemDTO saveItem(ItemDTO item) {
        return convert.convertToItemDTO(itemDao.save(convert.convertToItemEntity(item)));

    }

    @Override
    public List<ItemDTO> getAllItem() {
        return convert.getItemDTOList(itemDao.findAll());

    }

    @Override
    public ItemDTO getSelectedItem(String id) {
        if (!itemDao.existsById(id)) throw new NotFoundException("ITEM NOT FOUND");
        return convert.convertToItemDTO(itemDao.getReferenceById(id));
    }
    @Override
    public void deleteItem(String id) {
        if (!itemDao.existsById(id)) throw new NotFoundException("Item NOT FOUND");
        itemDao.deleteById(id);
    }

    @Override
    public void updateItem(String id, ItemDTO item) {
        Optional<ItemEntity> tmpItem = itemDao.findById(id);
        if (!tmpItem.isPresent()) throw new NotFoundException("ITEM NOT FOUND");
        tmpItem.get().setDescription(item.getDescription());
        tmpItem.get().setQty(item.getQty());
        tmpItem.get().setUnitPrice(item.getUnitPrice());
}
}
