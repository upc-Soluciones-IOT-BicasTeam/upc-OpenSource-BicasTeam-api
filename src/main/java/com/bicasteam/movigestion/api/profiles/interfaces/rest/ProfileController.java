package com.bicasteam.movigestion.api.profiles.interfaces.rest;

import com.bicasteam.movigestion.api.profiles.domain.model.aggregates.Profile;
import com.bicasteam.movigestion.api.profiles.domain.model.commands.CreateProfileCommand;
import com.bicasteam.movigestion.api.profiles.domain.services.ProfileCommandService;
import com.bicasteam.movigestion.api.profiles.domain.services.ProfileQueryService;
import com.bicasteam.movigestion.api.profiles.interfaces.rest.resources.CreateProfileResource;
import com.bicasteam.movigestion.api.profiles.interfaces.rest.resources.ProfileResource;
import com.bicasteam.movigestion.api.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.bicasteam.movigestion.api.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileCommandService commandService;
    private final ProfileQueryService queryService;

    public ProfileController(ProfileCommandService commandService, ProfileQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        CreateProfileCommand command = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<Profile> result = commandService.handle(command);
        return result.map(profile -> new ResponseEntity<>(
                        ProfileResourceFromEntityAssembler.toResourceFromEntity(profile), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{idCredential}")
    public ResponseEntity<ProfileResource> getProfileByCredentialId(@PathVariable Long idCredential) {
        Optional<Profile> result = queryService.findByCredentialId(idCredential);
        return result.map(profile -> ResponseEntity.ok(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        List<Profile> profiles = queryService.findAll();
        List<ProfileResource> resources = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long id, @RequestBody CreateProfileResource resource) {
        CreateProfileCommand command = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        boolean updated = commandService.update(id, command);
        if (updated) {
            Optional<Profile> result = queryService.findById(id);
            return result.map(profile -> ResponseEntity.ok(ProfileResourceFromEntityAssembler.toResourceFromEntity(profile)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        boolean deleted = commandService.delete(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
