package io.mzlnk.identitybroker.server.application.security.auth.credentials;

public record JwtAuthCredentials(String jwtToken,
                                 Long userId) {

}
