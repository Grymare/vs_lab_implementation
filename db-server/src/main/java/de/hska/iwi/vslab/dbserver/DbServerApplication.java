package de.hska.iwi.vslab.dbserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
public class DbServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbServerApplication.class, args);
	}

}
