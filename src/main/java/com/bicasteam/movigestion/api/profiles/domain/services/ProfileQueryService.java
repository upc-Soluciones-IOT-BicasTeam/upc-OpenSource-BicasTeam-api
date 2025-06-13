package com.bicasteam.movigestion.api.profiles.domain.services;

import com.bicasteam.movigestion.api.profiles.domain.model.aggregates.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> findByCredentialId(Long idCredential);
    Optional<Profile> findById(Long id);
    List<Profile> findAll();
}
