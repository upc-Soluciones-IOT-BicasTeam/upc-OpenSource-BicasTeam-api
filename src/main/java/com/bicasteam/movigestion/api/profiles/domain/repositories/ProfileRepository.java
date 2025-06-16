package com.bicasteam.movigestion.api.profiles.domain.repositories;

import com.bicasteam.movigestion.api.profiles.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    // Eliminar este método:
    // Optional<Profile> findByUserId(Long userId);

    // Si necesitas buscar por idCredential, usa:
    Optional<Profile> findByIdCredential(Long idCredential);
}
