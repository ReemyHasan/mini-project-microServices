package com.miniproject.inventoryservice.service;

import com.miniproject.inventoryservice.model.Inventory;
import com.miniproject.inventoryservice.repository.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepo inventoryRepo;

    @Transactional(readOnly = true)
    public boolean isInStock(String code ){
        return inventoryRepo.findByCode(code).isPresent();


    }
}
