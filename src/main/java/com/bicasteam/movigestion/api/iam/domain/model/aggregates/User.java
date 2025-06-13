package com.bicasteam.movigestion.api.iam.domain.model.aggregates;

import com.bicasteam.movigestion.api.iam.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String role;

    public User(CreateUserCommand command) {
        this.email = command.email();
        this.password = command.password();
        this.role = command.role();
    }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setRole(String role) { this.role = role; }
}
