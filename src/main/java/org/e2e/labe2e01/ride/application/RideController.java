package org.e2e.labe2e01.ride.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.ride.domain.RideService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ride")
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

    @GetMapping("/{passengerId}")
    public ResponseEntity<Page<Ride>> getRidesByPassenger(
            @PathVariable Long passengerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Ride> rides = rideService.getRidesByPassenger(passengerId, PageRequest.of(page, size));
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Ride> updateRide(@PathVariable Long id, @RequestBody Ride ride) {
        Ride updatedRide = rideService.updateRide(id, ride);
        return new ResponseEntity<>(updatedRide, HttpStatus.OK);
    }

    @PatchMapping("/{rideId}/assign/{driverId}")
    public ResponseEntity<Ride> assignDriver(@PathVariable Long rideId, @PathVariable Long driverId) {
        Ride updatedRide = rideService.assignDriver(rideId, driverId);
        return new ResponseEntity<>(updatedRide, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        boolean isDeleted = rideService.deleteRide(id);
        return isDeleted
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
