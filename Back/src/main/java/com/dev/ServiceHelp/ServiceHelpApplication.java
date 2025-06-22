package com.dev.ServiceHelp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ServiceHelpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHelpApplication.class, args);
	}
}
