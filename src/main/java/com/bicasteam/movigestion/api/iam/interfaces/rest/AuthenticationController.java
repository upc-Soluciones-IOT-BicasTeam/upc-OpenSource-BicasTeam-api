package com.bicasteam.movigestion.api.iam.interfaces.rest;

import com.bicasteam.movigestion.api.iam.domain.services.UserCommandService;
import com.bicasteam.movigestion.api.iam.interfaces.rest.resources.AuthResource;
import com.bicasteam.movigestion.api.iam.interfaces.rest.resources.SignInResource;
import com.bicasteam.movigestion.api.iam.interfaces.rest.resources.SignUpResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {
    private final UserCommandService userSvc;
    public AuthenticationController(UserCommandService userSvc) { this.userSvc = userSvc; }
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/sign-up") public ResponseEntity<Long> signUp(@RequestBody SignUpResource r) {
        return userSvc.signUp(r.toCommand()).map(id -> ResponseEntity.status(HttpStatus.CREATED).body(id))
                .orElse(ResponseEntity.badRequest().build());
    }
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/sign-in") public ResponseEntity<AuthResource> signIn(@RequestBody SignInResource r) {
        return userSvc.signIn(r.toCommand()).map(tok->ResponseEntity.ok(new AuthResource(tok)))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
