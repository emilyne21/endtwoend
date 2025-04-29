package org.e2e.labe2e01.vehicle.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
    @Id
    private Long id;
    private String brand;
    private String model;
    private String licensePlate;
    private int fabricationYear;
    private int capacity;

}

