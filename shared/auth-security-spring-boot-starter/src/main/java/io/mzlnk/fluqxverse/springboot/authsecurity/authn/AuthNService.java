package io.mzlnk.fluqxverse.springboot.authsecurity.authn;

import io.mzlnk.fluqxverse.springboot.authsecurity.credentials.JwtAuthCredentials;

public interface AuthNService {

    Long getPrincipal(String token);
    JwtAuthCredentials getCredentials(String token);

}
