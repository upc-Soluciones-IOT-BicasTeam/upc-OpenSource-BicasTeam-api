package com.bicasteam.movigestion.api.iam.domain.services;

import com.bicasteam.movigestion.api.iam.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    List<User> findAll();
}
