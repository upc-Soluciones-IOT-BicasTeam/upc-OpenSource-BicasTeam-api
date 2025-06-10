package com.bicasteam.movigestion.api.vehicles.interfaces.rest.transform;

import com.bicasteam.movigestion.api.vehicles.domain.model.commands.CreateVehicleCommand;
import com.bicasteam.movigestion.api.vehicles.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {
    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource) {
        return new CreateVehicleCommand(
                resource.idManager(),
                resource.licensePlate(),
                resource.brand(),
                resource.model(),
                resource.temperature(),
                resource.humidity(),
                resource.maxLoad(),
                resource.driverId(),
                resource.vehicleImage(),
                resource.color(),
                resource.lastTechnicalInspectionDate(),
                resource.latitude(),
                resource.longitude()
        );
    }
}
