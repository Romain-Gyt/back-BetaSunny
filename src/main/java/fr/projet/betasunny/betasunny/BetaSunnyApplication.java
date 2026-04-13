package fr.projet.betasunny.betasunny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BetaSunnyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetaSunnyApplication.class, args);
    }

}
