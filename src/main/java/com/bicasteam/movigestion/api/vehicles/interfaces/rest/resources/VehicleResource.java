package com.bicasteam.movigestion.api.vehicles.interfaces.rest.resources;

import java.time.LocalDateTime;

public record VehicleResource(
        int id,
        int managerId,
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
        Double longitude,
        LocalDateTime createdAt
) {}
