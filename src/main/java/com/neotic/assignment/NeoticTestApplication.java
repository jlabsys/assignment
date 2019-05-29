package com.neotic.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class NeoticTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeoticTestApplication.class, args);
	}

}
