package fr.projet.betasunny.betasunny.bo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "spots")
@SQLDelete(sql = "UPDATE SPOTS SET spot_deleted_at = NOW() WHERE spot_id = ? ")
@SQLRestriction("spot_deleted_at IS NULL")
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(name="spot_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="spot_updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @Version
    @Column(name="spot_version")
    // Gère les conflits d'accès concurrents automatiquement
    private Long version;

    @Column(name="spot_deleted_at")
    private LocalDateTime deletedAt;

}
