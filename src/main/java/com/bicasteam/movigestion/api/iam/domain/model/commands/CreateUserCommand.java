package com.bicasteam.movigestion.api.iam.domain.model.commands;

public record CreateUserCommand(String email, String password, String role) {}
