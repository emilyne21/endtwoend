package org.e2e.labe2e01.passenger.domain;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.passenger.infrastructure.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;


    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }


    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }


    public Passenger saveOrUpdatePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }


    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

    @Transactional
    public void addPlace(Long passengerId, Coordinate coordinate, String description) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        passenger.addPlace(coordinate, description);
        passengerRepository.save(passenger);  // Guardamos el pasajero después de agregar el lugar
    }


    @Transactional
    public void removePlace(Long passengerId, Long coordinateId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        passenger.removePlace(coordinateId);
        passengerRepository.save(passenger);  // Guardamos el pasajero después de eliminar el lugar
    }
}

