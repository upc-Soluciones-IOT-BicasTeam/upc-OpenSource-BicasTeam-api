package com.bicasteam.movigestion.api.shipments.domain.model.aggregates;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.*;


@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Utilizar GenerationType.IDENTITY en lugar de GenerationType.SEQUENCE
    private Long id;
    private Long userId;
    private String destination;
    private String description;
    private LocalDateTime dateTime;
    private String status;

    // Constructor vacío
    public Shipment() {
    }

    // Constructor con todos los parámetros
    public Shipment(Long id, Long userId, String destination, String description, LocalDateTime dateTime, String status) {
        this.id = id;
        this.userId = userId;
        this.destination = destination;
        this.description = description;
        this.dateTime = dateTime;
        this.status = status;
    }

    public Shipment(Long userId, String destination, String description, LocalDateTime dateTime, String status) {
    }

    // Getters
    public Long getId() {
        return id;
    }

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

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Método para establecer la fecha y hora a partir de cadenas
    public void setDateTime(String date, String time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        this.dateTime = LocalDateTime.of(localDate, localTime);
    }
}