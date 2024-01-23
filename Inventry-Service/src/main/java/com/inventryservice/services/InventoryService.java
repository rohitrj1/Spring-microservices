package com.inventryservice.services;

import com.inventryservice.Dto.InventoryResponse;
import com.inventryservice.model.Inventory;
import com.inventryservice.repo.InventoryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;


    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("Checking Inventory...");
        List<InventoryResponse> list = inventoryRepo.findBySkuCodeIn(skuCode)
                .stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
        return list;
    }

    public List<Inventory> getAll() {
        return this.inventoryRepo.findAll();
    }
}
