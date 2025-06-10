package com.bicasteam.movigestion.api.vehicles.interfaces.rest.transform;

import com.bicasteam.movigestion.api.vehicles.domain.model.aggregates.Vehicle;
import com.bicasteam.movigestion.api.vehicles.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
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
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCreatedAt()
        );
    }
}
