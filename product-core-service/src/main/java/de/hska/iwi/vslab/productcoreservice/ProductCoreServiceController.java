package de.hska.iwi.vslab.productcoreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCoreServiceController {

    @Autowired
    private ProductRepo repo;

    @RequestMapping(value = "/Product/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Product> getUser(@PathVariable Long userId) {
        Product user = repo.findOne(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
