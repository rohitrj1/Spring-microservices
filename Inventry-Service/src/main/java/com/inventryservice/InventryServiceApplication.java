package com.inventryservice;

import com.inventryservice.model.Inventory;
import com.inventryservice.repo.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventryServiceApplication.class, args);

	}
	@Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepo){
		return args -> {

			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone_13_red");
			inventory1.setQuantity(0);

			inventoryRepo.save(inventory);
			inventoryRepo.save(inventory1);
			System.out.println("Inventory created...");
		};
	}

}
