package com.bicasteam.movigestion.api.subscriptions.application.internal;

import com.bicasteam.movigestion.api.subscriptions.domain.model.aggregates.Subscription;
import com.bicasteam.movigestion.api.subscriptions.domain.model.enums.SubscriptionState;
import com.bicasteam.movigestion.api.subscriptions.domain.repositories.SubscriptionRepository;
import com.bicasteam.movigestion.api.subscriptions.domain.services.SubscriptionQueryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Optional<Subscription> findById(Long id) {
        return subscriptionRepository.findById(id);
    }

    @Override
    public List<Subscription> findByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public List<Subscription> findByPaymentDate(LocalDate date) {
        return subscriptionRepository.findByPaymentDate(date);
    }

    @Override
    public List<Subscription> findByState(SubscriptionState state) {
        return subscriptionRepository.findByState(state);
    }
}
