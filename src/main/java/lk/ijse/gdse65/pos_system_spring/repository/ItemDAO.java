package lk.ijse.gdse65.pos_system_spring.repository;

import lk.ijse.gdse65.pos_system_spring.entity.CustomerEntity;
import lk.ijse.gdse65.pos_system_spring.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends JpaRepository<ItemEntity, String> {
}
