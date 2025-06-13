package com.bicasteam.movigestion.api.iam.application.internal;

import com.bicasteam.movigestion.api.iam.domain.model.aggregates.User;
import com.bicasteam.movigestion.api.iam.domain.model.commands.CreateUserCommand;
import com.bicasteam.movigestion.api.iam.domain.repositories.UserRepository;
import com.bicasteam.movigestion.api.iam.domain.services.UserCommandService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository repository;

    public UserCommandServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Optional<User> handle(CreateUserCommand command) {
        User user = new User(command);
        try {
            repository.save(user);
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean update(Long id, CreateUserCommand command) {
        return repository.findById(id).map(user -> {
            user.setEmail(command.email());
            user.setPassword(command.password());
            user.setRole(command.role());
            repository.save(user);
            return true;
        }).orElse(false);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
