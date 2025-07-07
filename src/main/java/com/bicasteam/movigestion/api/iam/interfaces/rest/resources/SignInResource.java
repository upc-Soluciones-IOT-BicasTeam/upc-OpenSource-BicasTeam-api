package com.bicasteam.movigestion.api.iam.interfaces.rest.resources;

import com.bicasteam.movigestion.api.iam.domain.model.commands.SignInCommand;

public record SignInResource(String email, String password) { public SignInCommand toCommand(){return new SignInCommand(email,password);} }

