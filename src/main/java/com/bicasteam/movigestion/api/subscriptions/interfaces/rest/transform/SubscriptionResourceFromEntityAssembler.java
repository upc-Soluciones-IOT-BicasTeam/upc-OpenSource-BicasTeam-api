package com.bicasteam.movigestion.api.subscriptions.interfaces.rest.transform;

import com.bicasteam.movigestion.api.subscriptions.domain.model.aggregates.Subscription;
import com.bicasteam.movigestion.api.subscriptions.interfaces.rest.resources.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {
    public static SubscriptionResource toResourceFromEntity(Subscription subscription) {
        return new SubscriptionResource(
                subscription.getId(),
                subscription.getUrl(),
                subscription.getPaymentDate(),
                subscription.getState(),
                subscription.getUser().getId()
        );
    }
}
