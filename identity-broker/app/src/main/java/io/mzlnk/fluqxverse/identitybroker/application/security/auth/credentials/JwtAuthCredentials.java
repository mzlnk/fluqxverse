package io.mzlnk.fluqxverse.identitybroker.application.security.auth.credentials;

public record JwtAuthCredentials(String jwtToken,
                                 Long userId) {

}
