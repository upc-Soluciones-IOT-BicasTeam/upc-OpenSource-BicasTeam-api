package com.bicasteam.movigestion.api.shipments.domain.model.commands;

import java.time.LocalDateTime;

public class CreateShipmentCommand {
    private Long userId;
    private String destination;
    private String description;
    private LocalDateTime dateTime;
    private String status;

    // Constructor con todos los parámetros
    public CreateShipmentCommand(Long userId, String destination, String description, LocalDateTime dateTime, String status) {
        this.userId = userId;
        this.destination = destination;
        this.description = description;
        this.dateTime = dateTime;
        this.status = status;
    }

    // Getters
    public Long getUserId() {
        return userId;
    }

    public String getDestination() {
        return destination;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }
}
