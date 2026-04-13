package fr.projet.betasunny.betasunny.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway(DataSource dataSource) {
        System.out.println("🚀 [DEBUG] Démarrage forcé de Flyway en Java...");

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate(); // Exécute la migration de force !

        System.out.println("✅ [DEBUG] Flyway a terminé son travail.");
        return flyway;
    }
}