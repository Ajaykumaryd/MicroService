package com.OodlesMicroService.ratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient   //to get register with Eureka Server
public class RatingServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);


	}
}
