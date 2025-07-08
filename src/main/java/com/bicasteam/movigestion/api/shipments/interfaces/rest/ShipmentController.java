package com.bicasteam.movigestion.api.shipments.interfaces.rest;

import com.bicasteam.movigestion.api.shipments.domain.model.aggregates.Shipment;
import com.bicasteam.movigestion.api.shipments.domain.model.commands.CreateShipmentCommand;
import com.bicasteam.movigestion.api.shipments.domain.model.queries.GetAllShipments;
import com.bicasteam.movigestion.api.shipments.domain.model.queries.GetShipmentByIdQuery;
import com.bicasteam.movigestion.api.shipments.domain.services.ShipmentCommandService;
import com.bicasteam.movigestion.api.shipments.domain.services.ShipmentQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {
    private final ShipmentCommandService shipmentCommandHandler;
    private final ShipmentQueryService shipmentQueryHandler;

    public ShipmentController(ShipmentCommandService shipmentCommandHandler, ShipmentQueryService shipmentQueryHandler) {
        this.shipmentCommandHandler = shipmentCommandHandler;
        this.shipmentQueryHandler = shipmentQueryHandler;
    }

    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody CreateShipmentCommand command) {
        Shipment createdShipment = shipmentCommandHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShipment);
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentQueryHandler.handle(new GetAllShipments());
        return ResponseEntity.ok(shipments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable Long id) {
        Shipment shipment = shipmentQueryHandler.handle(new GetShipmentByIdQuery(id));
        return ResponseEntity.ok(shipment);
    }

    // Otros manejadores de comandos y consultas según sea necesario, como para actualizar o eliminar un envío
}
