package com.gui.car_rental_userService.controllers;


import com.gui.car_rental_userService.entities.User;
import com.gui.car_rental_userService.enums.Provider;
import com.gui.car_rental_userService.models.LoginResponseDto;
import com.gui.car_rental_userService.models.RegisterDto;
import com.gui.car_rental_userService.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegisterDto body) {
        try {
            User registeredUser = authenticationService.registerUser(body.getFirstName(), body.getLastName(), body.getEmail(), Provider.CUSTOM,body.getPassword());
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            Map<String,String> errorMessage = new HashMap<>();
            errorMessage.put("message", "email already in use");
            return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String,String> errorMessage = new HashMap<>();
            errorMessage.put("message", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody RegisterDto body) {
        try {
            LoginResponseDto loginResponse = authenticationService.loginUser(body.getEmail(), body.getPassword());
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}