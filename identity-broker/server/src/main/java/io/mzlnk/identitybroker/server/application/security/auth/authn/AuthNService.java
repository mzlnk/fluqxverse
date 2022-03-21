package io.mzlnk.identitybroker.server.application.security.auth.authn;

import io.mzlnk.identitybroker.server.application.security.auth.credentials.JwtAuthCredentials;

import javax.servlet.http.HttpServletRequest;

public interface AuthNService {

    Long getPrincipal(String token);
    JwtAuthCredentials getCredentials(String token);

}
