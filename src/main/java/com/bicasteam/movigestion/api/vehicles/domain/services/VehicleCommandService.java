package com.bicasteam.movigestion.api.vehicles.domain.services;


import com.bicasteam.movigestion.api.vehicles.domain.model.aggregates.Vehicle;
import com.bicasteam.movigestion.api.vehicles.domain.model.commands.CreateVehicleCommand;

import java.util.Optional;

public interface VehicleCommandService {
    Optional<Vehicle> handle(CreateVehicleCommand command);
    void deleteById(int id);
    void save(Vehicle vehicle);

}

