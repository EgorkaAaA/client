package ru.lebedev.sber.client;

import ru.lebedev.sber.client.service.SenderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(ClientApplication.class, args);
		final SenderService sender = context.getBean(SenderService.class);
		sender.send();
		context.stop();
	}

}
