package org.qbit.challenge.challenge.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.qbit.challenge.challenge.dev.model")
@EnableJpaRepositories("org.qbit.challenge.challenge.dev.repository")
public class ChallengeDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeDevApplication.class, args);
	}
}
