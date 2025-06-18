package com.bicasteam.movigestion.api.vehicles.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;

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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime lastTechnicalInspectionDate,
        String location,
        String speed,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt
) {}
