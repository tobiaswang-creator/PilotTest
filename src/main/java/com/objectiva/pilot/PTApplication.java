package com.objectiva.pilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.objectiva.*")
public class PTApplication {

	public static void main(String[] args) {
		SpringApplication.run(PTApplication.class, args);
	}

}
