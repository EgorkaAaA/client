package ru.lebedev.sber.client.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDto {

	private UUID id = UUID.randomUUID();

	private LocalDateTime timeStampResponse = LocalDateTime.now();

	private UUID requestId;

	private LocalDateTime timeStampRequest;

	private AirportDto airport;

}
