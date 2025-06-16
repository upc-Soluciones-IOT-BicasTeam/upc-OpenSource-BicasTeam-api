package com.bicasteam.movigestion.api.profiles.interfaces.rest.transform;

import com.bicasteam.movigestion.api.profiles.domain.model.aggregates.Profile;
import com.bicasteam.movigestion.api.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile profile) {
        return new ProfileResource(
                profile.getId(),
                profile.getIdCredential(),
                profile.getName(),
                profile.getLastName(),
                profile.getTelephone(),
                profile.getIdCompany()
        );
    }
}
