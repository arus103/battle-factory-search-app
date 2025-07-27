package com.example.battlefactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BattleFactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleFactoryApplication.class, args);
	}
}
