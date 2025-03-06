package com.gui.car_rental_userService.feignTest;

import org.springframework.stereotype.Service;

@Service
public class InventoryFeignService {

    private final InventoryClient inventoryClient;

    public InventoryFeignService(InventoryClient inventoryClient){
        this.inventoryClient = inventoryClient;
    }

    public String testInventoryStatus(){
        return inventoryClient.testInventory();
    }


}
