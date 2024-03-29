package ru.spbifuture.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args).close();
	}

}
