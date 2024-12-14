package com.hotdog.saas.domain.foundation;

import com.hotdog.saas.domain.model.Login;

public interface AuthService {

    String generateToken(Login login);

    void verifyToken(String token);

    String extractUsername(String token);
}
