package com.bicasteam.movigestion.api.vehicles.interfaces.rest.transform;

import com.bicasteam.movigestion.api.vehicles.domain.model.aggregates.Vehicle;
import com.bicasteam.movigestion.api.vehicles.domain.model.valueObjects.VehicleLocation;
import com.bicasteam.movigestion.api.vehicles.interfaces.rest.resources.VehicleResource;

import java.time.format.DateTimeFormatter;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        VehicleLocation loc = entity.getLocation();
        String gpsDateFormatted = (loc != null && loc.getGpsDateTime() != null)
                ? loc.getGpsDateTime().format(formatter)
                : null;

        String locationString = String.format("Latitude: %s, Longitude: %s, Altitude: %s, GPS Date&Time: %s",
                loc != null ? loc.getLatitude() : "N/A",
                loc != null ? loc.getLongitude() : "N/A",
                loc != null && loc.getAltitude() != null ? loc.getAltitude() : 0.0,
                gpsDateFormatted);

        String speedFormatted = (loc != null && loc.getSpeed() != null)
                ? String.format("%.1f km/h", loc.getSpeed())
                : "N/A km/h";

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
