package lk.ijse.gdse65.pos_system_spring.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse65.pos_system_spring.dto.ItemDTO;
import lk.ijse.gdse65.pos_system_spring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class Item {
    @Autowired
    ItemService itemService;

    @GetMapping("/product")
    public String checkItem(){
        return "Check Item";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ItemDTO saveItem(@Valid
                            @RequestPart("item_code")String item_code,
                            @RequestPart ("description") String description,
                            @RequestPart ("qty") String qty,
                            @RequestPart ("unitPrice") String unitPrice,
                            Errors errors){
        if (errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }

        ItemDTO buildItem = new ItemDTO();
        buildItem.setItem_code(item_code);
        buildItem.setDescription(description);
        buildItem.setQty(Integer.parseInt(qty));
        buildItem.setUnitPrice(Double.parseDouble(unitPrice));

        return itemService.saveItem(buildItem);
}

    @GetMapping(value = "/{item_code}",produces = "application/json")
    ResponseEntity<ItemDTO> getSelectedItem(@PathVariable ("item_code") String id){
        ItemDTO selectedItem = itemService.getSelectedItem(id);
        return selectedItem != null ? ResponseEntity.ok(selectedItem) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(produces = "application/json")
    List<ItemDTO> getAllItem(){
        return itemService.getAllItem();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{item_code}")
    public void deleteItem(@PathVariable ("item_code") String id){
        itemService.deleteItem(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateCustomer(@Valid
                               @RequestPart ("description") String description,
                               @RequestPart ("qty") String qty,
                               @RequestParam ("unitPrice") String unitPrice,
                               @RequestPart ("item_code") String id,
                               Errors errors){
        if(errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }
        ItemDTO updatedBuildItem = new ItemDTO();
        updatedBuildItem.setDescription(description);
        updatedBuildItem.setQty(Integer.parseInt(qty));
        updatedBuildItem.setUnitPrice(Double.parseDouble(unitPrice));

        itemService.updateItem(id,updatedBuildItem);
}
}
