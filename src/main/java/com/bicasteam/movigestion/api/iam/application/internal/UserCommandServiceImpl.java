package com.bicasteam.movigestion.api.iam.application.internal;

import com.bicasteam.movigestion.api.iam.domain.model.aggregates.User;
import com.bicasteam.movigestion.api.iam.domain.model.commands.CreateUserCommand;
import com.bicasteam.movigestion.api.iam.domain.model.commands.SignInCommand;
import com.bicasteam.movigestion.api.iam.domain.model.commands.SignUpCommand;
import com.bicasteam.movigestion.api.iam.domain.repositories.UserRepository;
import com.bicasteam.movigestion.api.iam.domain.services.UserCommandService;
import com.bicasteam.movigestion.api.iam.infrastructure.hashing.HashingService;
import com.bicasteam.movigestion.api.iam.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository repository;
    private final HashingService hashing;
    private final BearerTokenService tokenService;
    public UserCommandServiceImpl(UserRepository repository,
                                  HashingService hashing,
                                  BearerTokenService tokenService) {
        this.repository = repository;
        this.hashing = hashing;
        this.tokenService = tokenService;
    }
    @Override @Transactional
    public Optional<Long> signUp(SignUpCommand cmd) {
        if (repository.findByEmail(cmd.email()).isPresent()) return Optional.empty();
        String hashed = hashing.hash(cmd.password());
        User user = new User(cmd.email(), hashed, cmd.role());
        User saved = repository.save(user);
        return Optional.of(saved.getId());
    }
    @Override
    public Optional<String> signIn(SignInCommand cmd) {
        return repository.findByEmail(cmd.email())
                .filter(u -> hashing.matches(cmd.password(), u.getPassword()))
                .map(tokenService::generateToken);
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

    @Override
    @Transactional
    public Optional<User> createUser(CreateUserCommand command) {
        if (repository.existsByEmail(command.email())) return Optional.empty();
        User user = new User(command.email(), command.password(), command.role()); // Asegúrate de tener este constructor
        return Optional.of(repository.save(user));
    }
}
