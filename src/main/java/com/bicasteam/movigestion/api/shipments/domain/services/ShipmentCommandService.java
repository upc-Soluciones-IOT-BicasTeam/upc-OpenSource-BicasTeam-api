package com.bicasteam.movigestion.api.shipments.domain.services;

import com.bicasteam.movigestion.api.shipments.application.internal.commandservices.ShipmentCommandServiceImpl;
import com.bicasteam.movigestion.api.shipments.domain.model.aggregates.Shipment;
import com.bicasteam.movigestion.api.shipments.domain.model.commands.CreateShipmentCommand;
import org.springframework.stereotype.Component;

@Component
public class ShipmentCommandService {
    private final ShipmentCommandServiceImpl shipmentCommandService;

    public ShipmentCommandService(ShipmentCommandServiceImpl shipmentCommandService) {
        this.shipmentCommandService = shipmentCommandService;
    }

    public Shipment handle(CreateShipmentCommand command) {
        Shipment shipment = new Shipment(
                command.getUserId(),
                command.getDestination(),
                command.getDescription(),
                command.getDateTime(),
                command.getStatus()
        );
        return shipmentCommandService.createShipment(shipment);
    }

    // Otros manejadores de comandos según sea necesario, como para actualizar o eliminar un envío
}