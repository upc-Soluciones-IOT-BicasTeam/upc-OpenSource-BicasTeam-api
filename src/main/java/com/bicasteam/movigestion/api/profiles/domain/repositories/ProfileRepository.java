package com.bicasteam.movigestion.api.profiles.domain.repositories;

import com.bicasteam.movigestion.api.profiles.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserId(Long idCredential);
    boolean existsByUserId(Long idCredential);
}
