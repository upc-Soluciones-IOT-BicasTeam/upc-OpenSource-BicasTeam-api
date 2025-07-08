package com.bicasteam.movigestion.api.vehicles.interfaces.rest.transform;

import com.bicasteam.movigestion.api.vehicles.domain.model.aggregates.Vehicle;
import com.bicasteam.movigestion.api.vehicles.interfaces.rest.resources.VehicleResource;

import java.time.format.DateTimeFormatter;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String gpsDateFormatted = entity.getGPSDateTime() != null ?
                entity.getGPSDateTime().format(formatter) : null;

        String locationString = String.format("Latitude: %s, Longitude: %s, Altitude: %s, GPS Date&Time: %s",
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getAltitude() != null ? entity.getAltitude() : 0.0,
                gpsDateFormatted);

        String speedFormatted;
        if (entity.getSpeed() != null) {
            speedFormatted = String.format("%.1f km/h", entity.getSpeed());
        } else {
            speedFormatted = "N/A km/h";
        }

        return new VehicleResource(
                entity.getId(),
                entity.getIdManager(),
                entity.getLicensePlate(),
                entity.getBrand(),
                entity.getModel(),
                entity.getTemperature(),
                entity.getHumidity(),
                entity.getMaxLoad(),
                entity.getDriverId(),
                entity.getVehicleImage(),
                entity.getColor(),
                entity.getLastTechnicalInspectionDate(),
                locationString,
                speedFormatted,
                entity.getCreatedAt()
        );
    }
}
