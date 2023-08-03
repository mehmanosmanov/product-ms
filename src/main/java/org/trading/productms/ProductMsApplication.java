package org.trading.productms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
@EnableSpringConfigured
public class ProductMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMsApplication.class, args);
	}
}
