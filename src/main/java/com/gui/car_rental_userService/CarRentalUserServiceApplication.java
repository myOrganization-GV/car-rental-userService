package com.gui.car_rental_userService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CarRentalUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalUserServiceApplication.class, args);
	}

}
