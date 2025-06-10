package com.bicasteam.movigestion.api.subscriptions.interfaces.rest.transform;

import com.bicasteam.movigestion.api.subscriptions.domain.model.commands.CreateSubscriptionCommand;
import com.bicasteam.movigestion.api.subscriptions.interfaces.rest.resources.CreateSubscriptionResource;

public class CreateSubscriptionCommandFromResourceAssembler {
    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(
                resource.url(), resource.paymentDate(), resource.state(), resource.userId()
        );
    }
}
