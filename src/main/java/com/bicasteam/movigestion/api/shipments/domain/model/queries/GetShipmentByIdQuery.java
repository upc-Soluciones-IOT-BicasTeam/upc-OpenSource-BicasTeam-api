package com.bicasteam.movigestion.api.shipments.domain.model.queries;

public class GetShipmentByIdQuery {
    private Long id;

    // Constructor
    public GetShipmentByIdQuery(Long id) {
        this.id = id;
    }

    // Getter
    public Long getId() {
        return id;
    }
}