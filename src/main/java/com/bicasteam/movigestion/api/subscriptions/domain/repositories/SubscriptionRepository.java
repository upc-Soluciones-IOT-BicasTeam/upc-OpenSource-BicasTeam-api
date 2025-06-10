package com.bicasteam.movigestion.api.subscriptions.domain.repositories;

import com.bicasteam.movigestion.api.subscriptions.domain.model.aggregates.Subscription;
import com.bicasteam.movigestion.api.subscriptions.domain.model.enums.SubscriptionState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);
    List<Subscription> findByPaymentDate(LocalDate date);
    List<Subscription> findByState(SubscriptionState state);
    void deleteByUserId(Long userId);
}
