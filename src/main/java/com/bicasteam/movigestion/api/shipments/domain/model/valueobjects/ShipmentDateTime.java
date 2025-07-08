package com.bicasteam.movigestion.api.shipments.domain.model.valueobjects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ShipmentDateTime {
    private LocalDate date;
    private LocalTime time;

    // Constructor vacío
    public ShipmentDateTime() {
    }

    // Constructor con todos los parámetros
    public ShipmentDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    // Getters
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    // Setters
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    // Método para establecer la fecha y hora a partir de cadenas
    public void setDateTime(String dateString, String timeString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.date = LocalDate.parse(dateString, dateFormatter);
        this.time = LocalTime.parse(timeString, timeFormatter);
    }
}