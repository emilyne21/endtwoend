package org.e2e.labe2e01.userLocations.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.passenger.domain.Passenger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserLocation {
    @EmbeddedId
    private PassengerCoordinateId id;

    @JoinColumn(name = "passenger_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("passengerId")
    private Passenger passenger;

    @JoinColumn(name = "coordinate_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("coordinateId")
    private Coordinate coordinate;

    @Column(nullable = false)
    private String description;

    public UserLocation(Passenger passenger, Coordinate coordinate, String description) {
        this.passenger = passenger;
        this.coordinate = coordinate;
        this.description = description;
        this.id = new PassengerCoordinateId();
    }

}