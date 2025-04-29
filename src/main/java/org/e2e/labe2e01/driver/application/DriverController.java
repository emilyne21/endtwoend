package org.e2e.labe2e01.driver.application;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.driver.domain.DriverService;
import org.e2e.labe2e01.vehicle.domain.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long id) {
        Driver driver = driverService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found"));
        return ResponseEntity.ok(driver);
    }

    @PostMapping
    public ResponseEntity<Driver> createDriver(@Valid @RequestBody Driver driver) {
        Driver createdDriver = driverService.save(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDriver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Driver> deleteDriver(@PathVariable Long id) {
        if (!driverService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found");
        }
        driverService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @Valid @RequestBody Driver driver) {
        if (!driverService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found");
        }
        driver.setId(id);
        Driver updatedDriver = driverService.save(driver);
        return ResponseEntity.ok(updatedDriver);
    }

    @PatchMapping("/{id}/location")
    public ResponseEntity<Driver> updateLocation(
            @PathVariable Long id,
            @RequestParam double latitude,
            @RequestParam double longitude) {

        Driver driver = driverService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found"));

        driver = driverService.updateLocation(driver, latitude, longitude);
        return ResponseEntity.ok(driver);
    }

    @PatchMapping("/{id}/car")
    public ResponseEntity<Driver> assignCar(
            @PathVariable Long id,
            @Valid @RequestBody Vehicle vehicle) {

        Driver driver = driverService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found"));

        driver = driverService.assignVehicle(driver, vehicle);
        return ResponseEntity.ok(driver);
    }
}