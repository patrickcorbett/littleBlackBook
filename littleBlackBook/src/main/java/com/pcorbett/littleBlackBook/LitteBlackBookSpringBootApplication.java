package com.pcorbett.littleBlackBook;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
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
		ConfigurableApplicationContext ctx = SpringApplication.run(LitteBlackBookSpringBootApplication.class, args);
		
		System.out.println("BEANS ### : " + ctx.getBeanDefinitionCount());
		
		int c = 1;
		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println("[" + c++ + "] : " +beanName);
		}
		
	}
}