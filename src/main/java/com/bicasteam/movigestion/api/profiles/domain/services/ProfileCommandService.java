package com.bicasteam.movigestion.api.profiles.domain.services;

import com.bicasteam.movigestion.api.profiles.domain.model.aggregates.Profile;
import com.bicasteam.movigestion.api.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
    boolean update(Long id, CreateProfileCommand command);
    boolean delete(Long id);
}
