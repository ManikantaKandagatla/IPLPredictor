package com.iplpredictor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IplPredictionApplication {

	public static void main(String[] args) {
		SpringApplication.run(IplPredictionApplication.class, args);
	}

}
