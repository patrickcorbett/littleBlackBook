package com.pcorbett.littleBlackBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Patrick Corbett
 *
 * @created 20.07.2018
 */
@SpringBootApplication
@ImportResource("classpath:spring/applicationContext.xml")
public class LitteBlackBookSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(LitteBlackBookSpringBootApplication.class, args);
	}
}