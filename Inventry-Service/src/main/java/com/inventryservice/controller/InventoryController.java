package com.inventryservice.controller;

import com.inventryservice.Dto.InventoryResponse;
import com.inventryservice.model.Inventory;
import com.inventryservice.services.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


//    http://localhost:7073/api/inventory/iphone_13
//    http://localhost:7073/api/inventory?skuCode = iphone_13 & skuCode = iphone_13_red is good for readeable @RequestParam
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }

//    @GetMapping
//    public List<Inventory> getAll(){
//        return this.inventoryService.getAll();
//    }
}
