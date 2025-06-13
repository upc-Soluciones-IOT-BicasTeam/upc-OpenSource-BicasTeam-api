package com.bicasteam.movigestion.api.iam.domain.model.queries;

public record GetUserByEmailAndPasswordQuery(String email, String password) {
}
