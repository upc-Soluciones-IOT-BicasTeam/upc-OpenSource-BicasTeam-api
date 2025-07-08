package com.bicasteam.movigestion.api.shipments.domain.services;

import com.bicasteam.movigestion.api.shipments.application.internal.queryservices.ShipmentQueryServiceImpl;
import com.bicasteam.movigestion.api.shipments.domain.model.aggregates.Shipment;
import com.bicasteam.movigestion.api.shipments.domain.model.queries.GetAllShipments;
import com.bicasteam.movigestion.api.shipments.domain.model.queries.GetShipmentByIdQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShipmentQueryService {
    private final ShipmentQueryServiceImpl shipmentQueryService;

    public ShipmentQueryService(ShipmentQueryServiceImpl shipmentQueryService) {
        this.shipmentQueryService = shipmentQueryService;
    }

    public List<Shipment> handle(GetAllShipments query) {
        return shipmentQueryService.getAllShipments();
    }

    public Shipment handle(GetShipmentByIdQuery query) {
        return shipmentQueryService.getShipmentById(query.getId());
    }

    // Otros manejadores de consultas según sea necesario, como buscar por estado, fecha, etc.
}