package org.e2e.labe2e01.vehicle.domain;

import org.e2e.labe2e01.vehicle.infrastructure.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findVehiclesByBrandsAndYear(List<String> brands, int fabricationYear) {
        return vehicleRepository.findByBrandInAndFabricationYearGreaterThanEqual(brands, fabricationYear);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    public void deleteAllVehicles() {
        vehicleRepository.deleteAll();
    }
}

