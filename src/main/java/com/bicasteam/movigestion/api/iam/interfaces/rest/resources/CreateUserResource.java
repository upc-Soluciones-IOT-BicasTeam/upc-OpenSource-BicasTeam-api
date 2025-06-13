package com.bicasteam.movigestion.api.iam.interfaces.rest.resources;

public record CreateUserResource(String email, String password, String role) {}
