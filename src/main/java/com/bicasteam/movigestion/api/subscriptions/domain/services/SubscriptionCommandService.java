package com.bicasteam.movigestion.api.subscriptions.domain.services;

import com.bicasteam.movigestion.api.subscriptions.domain.model.aggregates.Subscription;
import com.bicasteam.movigestion.api.subscriptions.domain.model.commands.CreateSubscriptionCommand;

import java.util.Optional;

public interface SubscriptionCommandService {
    Optional<Subscription> handle(CreateSubscriptionCommand command);
    boolean updateByUserId(Long userId, CreateSubscriptionCommand command);
    boolean deleteById(Long id);
    boolean deleteByUserId(Long userId);
}
