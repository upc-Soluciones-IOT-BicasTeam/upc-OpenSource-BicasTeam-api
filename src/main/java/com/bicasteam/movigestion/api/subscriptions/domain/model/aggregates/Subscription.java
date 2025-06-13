package com.bicasteam.movigestion.api.subscriptions.domain.model.aggregates;

import com.bicasteam.movigestion.api.iam.domain.model.aggregates.User;
import com.bicasteam.movigestion.api.subscriptions.domain.model.commands.CreateSubscriptionCommand;
import com.bicasteam.movigestion.api.subscriptions.domain.model.enums.SubscriptionState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    private SubscriptionState state;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user")
    private User user;

    public Subscription(CreateSubscriptionCommand command, User user) {
        this.url = command.url();
        this.paymentDate = command.paymentDate();
        this.state = command.state();
        this.user = user;
    }

    public void update(String url, LocalDate paymentDate, SubscriptionState state) {
        this.url = url;
        this.paymentDate = paymentDate;
        this.state = state;
    }
}
