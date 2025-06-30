package com.example.gadanko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GadankoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GadankoApplication.class, args);
	}

}
