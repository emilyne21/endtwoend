package org.e2e.labe2e01.passenger.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.user.domain.User;
import org.e2e.labe2e01.userLocations.domain.UserLocation;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Passenger extends User {
    @OneToMany(mappedBy = "passenger",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<UserLocation> places = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "FLOAT DEFAULT 0.0")
    @Min(0) @Max(5)
    private Float rating = 0.0f;

    @OneToMany(mappedBy = "passenger",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Ride> rides = new ArrayList<>();

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    public List<Coordinate> getPlacesList() {
        List<Coordinate> coordinates = new ArrayList<>();

        for (UserLocation userLocation : this.places) {
            Coordinate newCoordinate = new Coordinate(userLocation.getCoordinate().getLatitude(), userLocation.getCoordinate().getLongitude());
            newCoordinate.setId(userLocation.getCoordinate().getId());
            coordinates.add(newCoordinate);
        }

        return coordinates;
    }

    public void addPlace(Coordinate coordinate, String description) {
        UserLocation userLocation = new UserLocation(this, coordinate, description);
        places.add(userLocation);
        coordinate.getPassengers().add(userLocation);
    }

    public void removePlace(Long coordinateId) {
        for (UserLocation userLocation : places) {
            if (userLocation.getPassenger().equals(this) && Objects.equals(userLocation.getCoordinate().getId(), coordinateId)) {
                places.remove(userLocation);
                userLocation.getCoordinate().getPassengers().remove(userLocation);
                userLocation.setPassenger(null);
                userLocation.setCoordinate(null);
                break;
            }
        }
    }



}