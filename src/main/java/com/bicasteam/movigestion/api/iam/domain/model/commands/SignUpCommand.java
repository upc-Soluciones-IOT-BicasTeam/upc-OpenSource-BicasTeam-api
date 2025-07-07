package com.bicasteam.movigestion.api.iam.domain.model.commands;

public record SignUpCommand(String email, String password, String role) {}
