package fr.projet.betasunny.betasunny.repository;

import fr.projet.betasunny.betasunny.bo.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {

}
