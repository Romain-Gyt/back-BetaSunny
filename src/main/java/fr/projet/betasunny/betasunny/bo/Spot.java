package fr.projet.betasunny.betasunny.bo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SPOTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="spot_id")
    private Long id;

    @Column(name="spot_name", nullable=false)
    private String name;

    @Column(name="spot_latitude")
    private Double latitude;

    @Column(name="spot_longitude")
    private Double longitude;

    @Column(name="spot_azimut")
    private Integer azimut;

}
