package com.bicasteam.movigestion.api.profiles.application.internal;

import com.bicasteam.movigestion.api.iam.domain.model.aggregates.User;
import com.bicasteam.movigestion.api.iam.domain.repositories.UserRepository;
import com.bicasteam.movigestion.api.profiles.domain.model.aggregates.Profile;
import com.bicasteam.movigestion.api.profiles.domain.model.commands.CreateProfileCommand;
import com.bicasteam.movigestion.api.profiles.domain.repositories.ProfileRepository;
import com.bicasteam.movigestion.api.profiles.domain.services.ProfileCommandService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Optional<Profile> handle(CreateProfileCommand command) {
        Optional<User> user = userRepository.findById(command.idCredential());
        if (user.isEmpty()) return Optional.empty();

        Profile company = null;
        if (command.idCompany() != null) {
            Optional<Profile> maybeCompany = profileRepository.findById(command.idCompany());
            if (maybeCompany.isEmpty()) return Optional.empty(); // idCompany no válido
            company = maybeCompany.get();
        }

        Profile profile = new Profile(command, user.get(), company);
        profileRepository.save(profile);
        return Optional.of(profile);
    }

    @Override
    @Transactional
    public boolean update(Long id, CreateProfileCommand command) {
        return profileRepository.findById(id).map(profile -> {
            profile.update(command.name(), command.lastName(), command.telephone());
            profileRepository.save(profile);
            return true;
        }).orElse(false);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
