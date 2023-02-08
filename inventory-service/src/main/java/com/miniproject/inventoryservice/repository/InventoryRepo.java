package com.miniproject.inventoryservice.repository;

import com.miniproject.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory,Long> {

    List<Inventory> findByCodeIn(List<String> code);
}
