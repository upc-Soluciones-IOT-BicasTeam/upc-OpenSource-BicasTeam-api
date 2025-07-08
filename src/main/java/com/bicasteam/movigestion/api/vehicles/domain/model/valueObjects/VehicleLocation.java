package com.bicasteam.movigestion.api.vehicles.domain.model.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Embeddable
public class VehicleLocation {

    private Double latitude;
    private Double longitude;
    private Double altitude;
    private Double speed;
    private LocalDateTime gpsDateTime;

    public VehicleLocation(Double latitude, Double longitude, Double altitude, Double speed) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
        this.gpsDateTime = LocalDateTime.now();
    }

    public void updateLocation(Double latitude, Double longitude, Double altitude, Double speed) {
        boolean hasChanged = false;

        if (latitude != null && !latitude.equals(this.latitude)) {
            this.latitude = latitude;
            hasChanged = true;
        }
        if (longitude != null && !longitude.equals(this.longitude)) {
            this.longitude = longitude;
            hasChanged = true;
        }
        if (altitude != null && !altitude.equals(this.altitude)) {
            this.altitude = altitude;
            hasChanged = true;
        }
        if (speed != null && !speed.equals(this.speed)) {
            this.speed = speed;
            hasChanged = true;
        }

        if (hasChanged) {
            this.gpsDateTime = LocalDateTime.now();
        }
    }
}