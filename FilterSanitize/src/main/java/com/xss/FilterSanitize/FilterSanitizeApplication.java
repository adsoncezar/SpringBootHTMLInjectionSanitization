package com.xss.FilterSanitize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.xss.FilterSanitize.repository")
public class FilterSanitizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilterSanitizeApplication.class, args);
	}

}
