package com.bicasteam.movigestion.api.vehicles.domain.model.aggregates;

import com.bicasteam.movigestion.api.vehicles.domain.model.commands.CreateVehicleCommand;
import com.bicasteam.movigestion.api.vehicles.domain.model.valueObjects.VehicleLocation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int idManager;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    private int temperature;
    private int humidity;
    private int maxLoad;
    private int driverId;
    private String vehicleImage;
    private String color;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTechnicalInspectionDate;

    @Embedded
    private VehicleLocation location;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public Vehicle(CreateVehicleCommand command) {
        this.idManager = command.idManager();
        this.licensePlate = command.licensePlate();
        this.brand = command.brand();
        this.model = command.model();
        this.temperature = command.temperature();
        this.humidity = command.humidity();
        this.maxLoad = command.maxLoad();
        this.driverId = command.driverId();
        this.vehicleImage = command.vehicleImage();
        this.color = command.color();
        this.lastTechnicalInspectionDate = command.lastTechnicalInspectionDate();
        this.location = new VehicleLocation(
                command.latitude(),
                command.longitude(),
                command.altitude(),
                command.speed()
        );
        this.createdAt = LocalDateTime.now();
    }

    public void updateLocation(Double latitude, Double longitude, Double altitude, Double speed) {
        if (this.location == null) {
            this.location = new VehicleLocation(latitude, longitude, altitude, speed);
        } else {
            this.location.updateLocation(latitude, longitude, altitude, speed);
        }
    }

    // Setters
    public void setIdManager(int idManager) {this.idManager = idManager;}

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setMaxLoad(int maxLoad) {this.maxLoad = maxLoad;}

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLastTechnicalInspectionDate(LocalDateTime lastTechnicalInspectionDate) {
        this.lastTechnicalInspectionDate = lastTechnicalInspectionDate;
    }
}