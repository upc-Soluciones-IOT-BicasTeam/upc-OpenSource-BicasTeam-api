package com.bicasteam.movigestion.api.subscriptions.domain.services;

import com.bicasteam.movigestion.api.subscriptions.domain.model.aggregates.Subscription;
import com.bicasteam.movigestion.api.subscriptions.domain.model.enums.SubscriptionState;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SubscriptionQueryService {
    List<Subscription> findAll();
    Optional<Subscription> findById(Long id);
    List<Subscription> findByUserId(Long userId);
    List<Subscription> findByPaymentDate(LocalDate date);
    List<Subscription> findByState(SubscriptionState state);
}
