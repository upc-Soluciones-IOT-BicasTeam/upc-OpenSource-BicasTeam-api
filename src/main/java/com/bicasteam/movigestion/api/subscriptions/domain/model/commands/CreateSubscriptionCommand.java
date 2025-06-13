package com.bicasteam.movigestion.api.subscriptions.domain.model.commands;

import com.bicasteam.movigestion.api.subscriptions.domain.model.enums.SubscriptionState;

import java.time.LocalDate;

public record CreateSubscriptionCommand(String url, LocalDate paymentDate, SubscriptionState state, Long userId) {
}
