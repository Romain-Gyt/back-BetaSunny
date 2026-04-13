package fr.projet.betasunny.betasunny;

import org.springframework.boot.SpringApplication;

public class TestBetaSunnyApplication {

    public static void main(String[] args) {
        SpringApplication.from(BetaSunnyApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
