package com.example.oopsem3lab2;

import com.example.oopsem3lab2.beans.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(scanBasePackages = "com.example.oopsem3lab2")
@EnableJpaRepositories(basePackages = "com.example.oopsem3lab2.repositories")
public class OopSem3Lab2Application {
	private static final Controller controller = new Controller();

	@Bean(name="controller")
	public Controller controller() {
		return controller;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(OopSem3Lab2Application.class, args);
	}

}
