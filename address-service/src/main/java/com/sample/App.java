package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class App {

	@Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }
	
	public static void main(String[] args) {
		System.out.println("Starting Address Service..");
		SpringApplication.run(App.class, args);
	}
}
