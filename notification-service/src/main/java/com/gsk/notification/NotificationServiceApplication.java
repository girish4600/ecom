package com.gsk.notification;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class
NotificationServiceApplication {


	@Value("${spring.data.mongodb.uri}")
	private String uri;

	@PostConstruct
	public void print() {
		System.out.println("MONGO URI = " + uri);
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
