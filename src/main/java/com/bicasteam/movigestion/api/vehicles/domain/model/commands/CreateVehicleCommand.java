package com.bicasteam.movigestion.api.vehicles.domain.model.commands;

import java.time.LocalDateTime;

public record CreateVehicleCommand(
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
        Double longitude,
        Double altitude,
        Double speed
) {}
