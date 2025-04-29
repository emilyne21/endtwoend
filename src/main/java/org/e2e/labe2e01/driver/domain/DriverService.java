package org.e2e.labe2e01.driver.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.driver.infrastructure.DriverRepository;
import org.e2e.labe2e01.vehicle.domain.Vehicle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    /**
     * Find a driver by ID
     */
    public Optional<Driver> findById(Long id) {
        return driverRepository.findById(id);
    }

    /**
     * Save a driver entity
     */
    @Transactional
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    /**
     * Check if a driver exists by ID
     */
    public boolean existsById(Long id) {
        return driverRepository.existsById(id);
    }

    /**
     * Delete a driver by ID
     */
    @Transactional
    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }

    /**
     * Update driver location
     */
    @Transactional
    public Driver updateDriverLocation(Driver driver, double latitude, double longitude) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }

        // Check if the driver already has a coordinate object
        if (driver.getCoordinate() == null) {
            // Create a new coordinate object if none exists
            Coordinate coordinate = new Coordinate();
            coordinate.setLatitude(latitude);
            coordinate.setLongitude(longitude);
            driver.setCoordinate(coordinate);
        } else {
            // Update existing coordinate
            driver.getCoordinate().setLatitude(latitude);
            driver.getCoordinate().setLongitude(longitude);
        }


        return driverRepository.save(driver);
    }


    /**
     * Assign a vehicle to a driver
     */
    @Transactional
    public Driver assignVehicle(Driver driver, Vehicle vehicle) {
        driver.setVehicle(vehicle);
        return driverRepository.save(driver);
    }

    /**
     * Find all drivers
     */
    public Iterable<Driver> findAll() {
        return driverRepository.findAll();
    }
}