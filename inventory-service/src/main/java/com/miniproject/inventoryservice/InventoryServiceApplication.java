package com.miniproject.inventoryservice;

import com.miniproject.inventoryservice.model.Inventory;
import com.miniproject.inventoryservice.repository.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepo){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setCode("SAMSUNG");
			inventory.setQuantity(10);

			Inventory inventory1 = new Inventory();
			inventory1.setCode("Samsung");
			inventory1.setQuantity(0);
			inventoryRepo.save(inventory);
			inventoryRepo.save(inventory1);
		};
	}

}
