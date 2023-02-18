package ru.lebedev.sber.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ServerRestService {

	private final RestTemplate restTemplate;

	@Value("${services.server.url}")
	private String backUrl;

	public <T> T post(String path, Class<T> tClass, Object o) {
		return restTemplate.postForEntity(
						backUrl + path,
						o,
						tClass
				)
				.getBody();
	}

}
