package com.bicasteam.movigestion.api.profiles.interfaces.rest.resources;

public record CreateProfileResource(Long idCredential, String name, String lastName, String telephone, Long idCompany) {}

