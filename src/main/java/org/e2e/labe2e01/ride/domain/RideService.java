package org.e2e.labe2e01.ride.domain;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.ride.infrastructure.RideRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RideService {
    private final RideRepository rideRepository;

    @Transactional
    public Ride createRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Optional<Ride> getRideById(Long id) {
        return rideRepository.findById(id);
    }

    @Transactional
    public Ride updateRide(Long id, Ride updatedRide) {
        if (rideRepository.existsById(id)) {
            updatedRide.setId(id);
            return rideRepository.save(updatedRide);
        }
        return null;
    }


    @Transactional
    public boolean deleteRide(Long id) {
        if (rideRepository.existsById(id)) {
            rideRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
