package com.and1ss.user_service.service.impl.password_hasher;

import java.security.NoSuchAlgorithmException;

public interface PasswordHasher {
    String hashPassword(String password) throws NoSuchAlgorithmException;
}