package com.miniproject.inventoryservice.service;

import com.miniproject.inventoryservice.dto.InventoryResponse;
import com.miniproject.inventoryservice.model.Inventory;
import com.miniproject.inventoryservice.repository.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepo inventoryRepo;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> code ){
        return inventoryRepo.findByCodeIn(code).stream()
                .map(
                        inventory ->
                            InventoryResponse.builder()
                                    .code(inventory.getCode())
                                    .isInStock(inventory.getQuantity() > 0)
                                    .build()

                ).toList();


    }
}
