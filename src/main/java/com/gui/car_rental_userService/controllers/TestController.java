package com.gui.car_rental_userService.controllers;

import com.gui.car_rental_userService.feignTest.InventoryFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class TestController {
    private final InventoryFeignService inventoryFeignService;

    public TestController(InventoryFeignService inventoryFeignService){
        this.inventoryFeignService = inventoryFeignService;
    }


    @GetMapping("/status")
    public String status(){
        return "user service working...";
    }
    @GetMapping("/inventory")
    public String inventoryStatus(){
        return "Inventory Status through User Service: " + inventoryFeignService.testInventoryStatus();
    }
}
