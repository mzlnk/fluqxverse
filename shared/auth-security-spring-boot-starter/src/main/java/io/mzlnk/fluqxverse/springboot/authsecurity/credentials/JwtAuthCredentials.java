package io.mzlnk.fluqxverse.springboot.authsecurity.credentials;

public record JwtAuthCredentials(String jwtToken,
                                 Long userId) {

}
