package org.e2e.labe2e01.ride.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.passenger.domain.Passenger;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String originName;

    @NotNull
    @Column(nullable = false)
    private String destinationName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "origin_coordinates_id", nullable = false)
    private Coordinate originCoordinates;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "destination_coordinates_id", nullable = false)
    private Coordinate destinationCoordinates;


    @NotNull
    @Column(nullable = false)
    private ZonedDateTime departureDate;

    @NotNull
    @Column(nullable = false)
    private ZonedDateTime arrivalDate;



    @NotNull
    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;


    @NotNull
    @Positive
    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    @AssertTrue(message = "La fecha de inicio debe ser menor que la fecha de llegada")
    public boolean isDepartureDateBeforeArrivalDate() {
        return departureDate != null && arrivalDate != null && departureDate.isBefore(arrivalDate);
    }

    @AssertTrue(message = "El origen y el destino no pueden ser las mismas coordenadas")
    public boolean isOriginAndDestinationDifferent() {
        return originName != null && destinationName != null && !originName.equals(destinationName);
    }

    public void setDriverId(Long driverId) {
    }
}
