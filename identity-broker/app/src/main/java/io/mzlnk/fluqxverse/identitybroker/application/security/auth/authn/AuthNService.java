package io.mzlnk.fluqxverse.identitybroker.application.security.auth.authn;

import io.mzlnk.fluqxverse.identitybroker.application.security.auth.credentials.JwtAuthCredentials;

public interface AuthNService {

    Long getPrincipal(String token);
    JwtAuthCredentials getCredentials(String token);

}
