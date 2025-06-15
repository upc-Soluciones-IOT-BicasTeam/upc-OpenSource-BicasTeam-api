package com.bicasteam.movigestion.api.profiles.domain.model.aggregates;

import com.bicasteam.movigestion.api.iam.domain.model.aggregates.User;
import com.bicasteam.movigestion.api.profiles.domain.model.commands.CreateProfileCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_credential", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_company")
    private Profile company; // representa al gerente (otro perfil)

    private String name;
    private String lastName;
    private String telephone;

    public Profile(CreateProfileCommand command, User user, Profile company) {
        this.name = command.name();
        this.lastName = command.lastName();
        this.telephone = command.telephone();
        this.user = user;
        this.company = company; // puede ser null si es gerente
    }

    public void update(String name, String lastName, String telephone) {
        this.name = name;
        this.lastName = lastName;
        this.telephone = telephone;
    }
}
