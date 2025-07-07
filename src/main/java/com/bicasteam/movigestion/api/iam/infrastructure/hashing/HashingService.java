package com.bicasteam.movigestion.api.iam.infrastructure.hashing;

public interface HashingService {
    String hash(String plain);
    boolean matches(String plain, String hash);
}
