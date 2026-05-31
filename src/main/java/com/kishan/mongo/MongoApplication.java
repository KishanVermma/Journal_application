package com.kishan.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
//		ConfigurableApplicationContext context=SpringApplication.run(MongoApplication.class,args);
//		ConfigurableEnvironment environment=context.getEnvironment();
//		System.out.println(environment.getActiveProfiles());
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
