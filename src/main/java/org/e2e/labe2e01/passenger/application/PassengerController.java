package org.e2e.labe2e01.passenger.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.passenger.domain.Passenger;
import org.e2e.labe2e01.passenger.domain.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        List<Passenger> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id) {
        Optional<Passenger> passenger = passengerService.getPassengerById(id);
        return passenger.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Passenger> createOrUpdatePassenger(@RequestBody Passenger passenger) {
        Passenger savedPassenger = passengerService.saveOrUpdatePassenger(passenger);
        return ResponseEntity.ok(savedPassenger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{passengerId}/places")
    public ResponseEntity<Void> addPlace(@PathVariable Long passengerId,
                                         @RequestBody Coordinate coordinate,
                                         @RequestParam String description) {
        passengerService.addPlace(passengerId, coordinate, description);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{passengerId}/places/{coordinateId}")
    public ResponseEntity<Void> removePlace(@PathVariable Long passengerId,
                                            @PathVariable Long coordinateId) {
        passengerService.removePlace(passengerId, coordinateId);
        return ResponseEntity.noContent().build();
    }
}