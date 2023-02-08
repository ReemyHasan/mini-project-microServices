package com.miniproject.inventoryservice.controller;

import com.miniproject.inventoryservice.dto.InventoryResponse;
import com.miniproject.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    //http://localhost:8082/api/inventory/code=lll&code=rrr
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> code ){

        return inventoryService.isInStock(code);
    }
}
