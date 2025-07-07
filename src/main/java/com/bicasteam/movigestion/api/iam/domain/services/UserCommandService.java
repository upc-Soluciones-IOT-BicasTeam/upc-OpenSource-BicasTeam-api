package com.bicasteam.movigestion.api.iam.domain.services;

import com.bicasteam.movigestion.api.iam.domain.model.aggregates.User;
import com.bicasteam.movigestion.api.iam.domain.model.commands.CreateUserCommand;
import com.bicasteam.movigestion.api.iam.domain.model.commands.SignInCommand;
import com.bicasteam.movigestion.api.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> createUser(CreateUserCommand command);
    Optional<Long> signUp(SignUpCommand cmd);
    Optional<String> signIn(SignInCommand cmd);
    boolean update(Long id, CreateUserCommand cmd);
    boolean delete(Long id);

}
