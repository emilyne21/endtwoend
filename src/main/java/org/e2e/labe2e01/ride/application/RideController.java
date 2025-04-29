package org.e2e.labe2e01.ride.application;


import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.ride.domain.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride) {
        Ride createdRide = rideService.createRide(ride);
        return new ResponseEntity<>(createdRide, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ride>> getAllRides() {
        List<Ride> rides = rideService.getAllRides();
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long id) {
        Optional<Ride> ride = rideService.getRideById(id);
        return ride.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ride> updateRide(@PathVariable Long id, @RequestBody Ride updatedRide) {
        Ride ride = rideService.updateRide(id, updatedRide);
        return ride != null ? new ResponseEntity<>(ride, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        boolean isDeleted = rideService.deleteRide(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}