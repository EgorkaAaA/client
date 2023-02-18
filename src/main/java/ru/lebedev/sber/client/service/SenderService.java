package ru.lebedev.sber.client.service;

import static java.lang.Thread.currentThread;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lebedev.sber.client.dto.RequestDto;
import ru.lebedev.sber.client.dto.ResponseDto;

@Service
@RequiredArgsConstructor
public class SenderService {

	private final ServerRestService restService;

	private final LoggingService logService;

	@Value("${services.client.thread}")

	private Long threads;

	@Value("${services.client.airports}")
	private Long airports;

	public void send() {
		final ExecutorService executor = Executors.newCachedThreadPool();
		for (long i = 0; i < threads; i++) {
			final long finalI = i;
			executor.execute(
					() -> {
						final long airportMaxId = airports / threads;
						for (long j = airportMaxId * finalI; j < airportMaxId * (finalI + 1); j++) {
							final RequestDto request = new RequestDto(
									UUID.randomUUID(),
									j,
									currentThread().getName(),
									LocalDateTime.now(Clock.systemDefaultZone())
							);
							logService.logAirportResponse(request, send(request));;
						}
					}
			);
		}
		executor.shutdown();
	}

	private ResponseDto send(final RequestDto request) {
		return restService.post("/airport", ResponseDto.class, request);
	}

}
