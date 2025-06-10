package com.bicasteam.movigestion.api.subscriptions.interfaces.rest.resources;

import com.bicasteam.movigestion.api.subscriptions.domain.model.enums.SubscriptionState;

import java.time.LocalDate;

public record CreateSubscriptionResource(String url, LocalDate paymentDate, SubscriptionState state, Long userId) {
}
