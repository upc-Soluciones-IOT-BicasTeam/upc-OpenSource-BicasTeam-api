package com.bicasteam.movigestion.api.iam.interfaces.rest.resources;

import com.bicasteam.movigestion.api.iam.domain.model.commands.SignUpCommand;

public record SignUpResource(String email, String password, String role) { public SignUpCommand toCommand(){return new SignUpCommand(email,password,role);} }

