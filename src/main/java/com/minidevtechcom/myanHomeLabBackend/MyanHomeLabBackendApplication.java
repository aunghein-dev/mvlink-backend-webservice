package com.minidevtechcom.myanHomeLabBackend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyanHomeLabBackendApplication {

	public static void main(String[] args) {
		// Load .env before Spring starts
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		// Inject environment variables into system properties
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(MyanHomeLabBackendApplication.class, args);
	}
}
