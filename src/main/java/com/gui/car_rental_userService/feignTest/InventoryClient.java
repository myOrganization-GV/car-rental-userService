package com.gui.car_rental_userService.feignTest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "car-rental-inventoryService")
public interface InventoryClient {
    @GetMapping("/inventory/status")
    String testInventory();
}
