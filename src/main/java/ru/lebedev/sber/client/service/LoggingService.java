package ru.lebedev.sber.client.service;

import static java.lang.Thread.currentThread;

import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.lebedev.sber.client.dto.AirportDto;
import ru.lebedev.sber.client.dto.RequestDto;
import ru.lebedev.sber.client.dto.ResponseDto;

@Service
@Log4j2
public class LoggingService {

	public void logAirportResponse(final RequestDto request, final ResponseDto response) {
		try {
			final Long id = Optional.of(response)
					.map(ResponseDto::getAirport)
					.map(AirportDto::getId)
					.orElseThrow(() -> new Exception("Airport with id: %s not found"));
			final boolean isNeededAirportAndRequest =
					request.getAirportId().equals(id) && request.getId().equals(response.getRequestId());
			log.info("Thread name: {} \nresponse: {}", request.getThreadName(), response);
			log.info("Is needed airport: {}", isNeededAirportAndRequest);
		} catch (Exception e) {
			log.error(
					"Error in thread: {} \nError message: {}",
					currentThread().getName(),
					e.getMessage().formatted(request.getAirportId())
			);
		}
	}

}
