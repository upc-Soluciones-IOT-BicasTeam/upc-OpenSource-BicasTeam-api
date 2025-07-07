package com.bicasteam.movigestion.api.iam.infrastructure.hashing.bcrypt;

import com.bicasteam.movigestion.api.iam.infrastructure.hashing.HashingService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class HashingServiceImpl implements HashingService {
    @Override public String hash(String plain) { return BCrypt.hashpw(plain, BCrypt.gensalt()); }
    @Override public boolean matches(String plain, String hash) { return BCrypt.checkpw(plain, hash); }
}