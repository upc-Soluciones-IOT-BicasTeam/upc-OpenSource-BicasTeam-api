package com.bicasteam.movigestion.api.iam.infrastructure.tokens.jwt;

public interface BearerTokenService {
    String generateToken(com.bicasteam.movigestion.api.iam.domain.model.aggregates.User user);
}
