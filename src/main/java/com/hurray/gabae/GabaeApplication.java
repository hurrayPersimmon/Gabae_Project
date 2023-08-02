package com.hurray.gabae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication
public class GabaeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GabaeApplication.class, args);
	}

}
