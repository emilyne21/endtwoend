package org.e2e.labe2e01.coordinate.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.e2e.labe2e01.userLocations.domain.UserLocation;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "coordinate")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @OneToMany(mappedBy = "coordinate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLocation> userLocations = new ArrayList<>();

    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public List<UserLocation> getPassengers() {
        return this.userLocations;
    }

}