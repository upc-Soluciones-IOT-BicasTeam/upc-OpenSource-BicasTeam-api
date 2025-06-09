package com.bicasteam.movigestion.api.iam.interfaces.rest;

import com.bicasteam.movigestion.api.iam.domain.model.aggregates.User;
import com.bicasteam.movigestion.api.iam.domain.model.commands.CreateUserCommand;
import com.bicasteam.movigestion.api.iam.domain.services.UserCommandService;
import com.bicasteam.movigestion.api.iam.domain.services.UserQueryService;
import com.bicasteam.movigestion.api.iam.interfaces.rest.resources.CreateUserResource;
import com.bicasteam.movigestion.api.iam.interfaces.rest.resources.UserResource;
import com.bicasteam.movigestion.api.iam.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.bicasteam.movigestion.api.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserCommandService commandService;
    private final UserQueryService queryService;

    public UserController(UserCommandService commandService, UserQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        CreateUserCommand command = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<User> result = commandService.handle(command);
        return result.map(user -> new ResponseEntity<>(
                        UserResourceFromEntityAssembler.toResourceFromEntity(user), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        Optional<User> result = queryService.findById(id);
        return result.map(user -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(user)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        List<User> users = queryService.findAll();
        List<UserResource> resources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResource> getUserByEmail(@PathVariable String email) {
        Optional<User> result = queryService.findByEmail(email);
        return result.map(user -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(user)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}/password/{password}")
    public ResponseEntity<UserResource> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        Optional<User> result = queryService.findByEmailAndPassword(email, password);
        return result.map(user -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(user)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody CreateUserResource resource) {
        CreateUserCommand command = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        boolean updated = commandService.update(id, command);
        if (updated) {
            Optional<User> user = queryService.findById(id);
            return user.map(value -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(value)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = commandService.delete(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
