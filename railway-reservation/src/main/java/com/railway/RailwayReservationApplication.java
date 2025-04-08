package com.railway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.railway") 
public class RailwayReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailwayReservationApplication.class, args);
	}

}
