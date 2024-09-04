package com.abhishek.plashoeApp.PlashoeApp;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PlashoeAppApplication {

	private static final Logger log = LoggerFactory.getLogger(PlashoeAppApplication.class);
	@Value("${DB_HOST_URL}")
	private String test;

	@Value("${DB_USERNAME}")
	private String user;
	@Value("${DB_PASSWORD}")
	private String pass;
	@Value("${DB_NAME}")
	private String name;

	public static void main(String[] args) {
		
		SpringApplication.run(PlashoeAppApplication.class, args);
	}

	@PostConstruct
	public void init() {
		log.info("RUNNIGNG.............");
		log.info(" checking DB_HOST_URL: {}", System.getenv("DB_HOST_URL"));
		log.info("DB_NAME: {}", System.getenv("DB_NAME"));
		log.info("DB_USERNAME: {}", System.getenv("DB_USERNAME"));
		log.info("DB_PASSWORD: {}", System.getenv("DB_PASSWORD"));
//	}
	}
}
