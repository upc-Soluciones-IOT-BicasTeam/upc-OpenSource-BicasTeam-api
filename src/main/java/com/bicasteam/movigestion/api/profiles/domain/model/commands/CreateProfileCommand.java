package com.bicasteam.movigestion.api.profiles.domain.model.commands;

public record CreateProfileCommand(Long idCredential, String name, String lastName, String telephone, Long idCompany) {
}
