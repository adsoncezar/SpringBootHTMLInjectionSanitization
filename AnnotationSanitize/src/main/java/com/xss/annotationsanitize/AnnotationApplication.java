package com.xss.annotationsanitize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.xss.AnnotationSanitize.repository")
public class AnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnotationApplication.class, args);
	}

}
