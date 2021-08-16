package de.hska.iwi.vslab.coreserviceuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
@Configuration


public class CoreServiceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreServiceUserApplication.class, args);
	}








	
}
