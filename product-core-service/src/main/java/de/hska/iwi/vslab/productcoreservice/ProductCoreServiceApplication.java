package de.hska.iwi.vslab.productcoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductCoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCoreServiceApplication.class, args);
	}

}
