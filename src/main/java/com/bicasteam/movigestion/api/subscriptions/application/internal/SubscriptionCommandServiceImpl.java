package com.bicasteam.movigestion.api.subscriptions.application.internal;

import com.bicasteam.movigestion.api.subscriptions.domain.model.aggregates.Subscription;
import com.bicasteam.movigestion.api.subscriptions.domain.model.commands.CreateSubscriptionCommand;
import com.bicasteam.movigestion.api.subscriptions.domain.repositories.SubscriptionRepository;
import com.bicasteam.movigestion.api.subscriptions.domain.services.SubscriptionCommandService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    @Transactional
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        Subscription subscription = new Subscription(command);
        try {
            subscriptionRepository.save(subscription);
            return Optional.of(subscription);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean updateByUserId(Long userId, CreateSubscriptionCommand command) {
        return subscriptionRepository.findByUserId(userId).stream().findFirst().map(subscription -> {
            subscription.update(command.url(), command.paymentDate(), command.state());
            subscriptionRepository.save(subscription);
            return true;
        }).orElse(false);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteByUserId(Long userId) {
        if (!subscriptionRepository.findByUserId(userId).isEmpty()) {
            subscriptionRepository.deleteByUserId(userId);
            return true;
        }
        return false;
    }
}
