package com.accion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.accion" })
public class AccionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccionDemoApplication.class, args);
	}
}
