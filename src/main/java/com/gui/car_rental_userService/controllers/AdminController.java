package com.gui.car_rental_userService.controllers;

import com.gui.car_rental_userService.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private AuthenticationService authenticationService;
    @GetMapping("/")
    public String helloAdmineController() {
        return "Admin level access";
    }


}