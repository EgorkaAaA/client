package ru.lebedev.sber.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

	@Bean
	@Scope(scopeName = "prototype")
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
