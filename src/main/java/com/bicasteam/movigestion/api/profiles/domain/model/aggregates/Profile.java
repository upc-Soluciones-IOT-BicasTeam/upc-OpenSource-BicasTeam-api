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

    private Long idCredential; // FK lógica a User
    private String name;
    private String lastName;
    private String telephone;
    private Long idCompany; // FK lógica a otro Profile

    public Profile(CreateProfileCommand command) {
        this.idCredential = command.idCredential();
        this.name = command.name();
        this.lastName = command.lastName();
        this.telephone = command.telephone();
        this.idCompany = command.idCompany();
    }

    public void update(String name, String lastName, String telephone, Long idCompany) {
        this.name = name;
        this.lastName = lastName;
        this.telephone = telephone;
        this.idCompany = idCompany;
    }
}
