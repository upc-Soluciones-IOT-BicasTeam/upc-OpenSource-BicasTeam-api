package com.bicasteam.movigestion.api.iam.domain.services;

import com.bicasteam.movigestion.api.iam.domain.model.aggregates.User;
import com.bicasteam.movigestion.api.iam.domain.model.commands.CreateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
    boolean update(Long id, CreateUserCommand command);
    boolean delete(Long id);
}
