package com.gui.car_rental_userService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class TestController {

    @GetMapping("/status")
    public String status(){
        return "user service working...";
    }

}
