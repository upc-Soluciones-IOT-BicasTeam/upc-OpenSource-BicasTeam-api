package com.bicasteam.movigestion.api.vehicles.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateVehicleResource(
        int idManager,
        String licensePlate,
        String brand,
        String model,
        int temperature,
        int humidity,
        int maxLoad,
        int driverId,
        String vehicleImage,
        String color,
        LocalDateTime lastTechnicalInspectionDate,
        Double latitude,
        Double longitude
) {}
