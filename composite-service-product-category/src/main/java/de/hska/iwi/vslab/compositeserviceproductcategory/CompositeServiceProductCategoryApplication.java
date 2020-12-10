package de.hska.iwi.vslab.compositeserviceproductcategory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;
// import org.springframework.cloud.netflix.ribbon.RibbonClient;
// @RibbonClient("composite-product-category")

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@Configuration
@EnableCircuitBreaker
public class CompositeServiceProductCategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompositeServiceProductCategoryApplication.class, args);
	}

}
