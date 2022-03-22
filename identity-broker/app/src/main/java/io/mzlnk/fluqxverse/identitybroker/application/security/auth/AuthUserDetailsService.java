package io.mzlnk.fluqxverse.identitybroker.application.security.auth;

import io.mzlnk.fluqxverse.identitybroker.application.security.auth.authz.AuthZException;
import io.mzlnk.fluqxverse.identitybroker.application.security.auth.authz.AuthZService;
import io.mzlnk.fluqxverse.identitybroker.application.security.auth.credentials.JwtAuthCredentials;
import io.mzlnk.fluqxverse.identitybroker.application.security.context.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;

@RequiredArgsConstructor
public class AuthUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final AuthZService authZService;

    @Override
    public AuthUserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) {
        Assert.notNull(token.getPrincipal(), "Principal cannot be null");
        Assert.notNull(token.getCredentials(), "Credentials cannot be null");
        Assert.isInstanceOf(JwtAuthCredentials.class, token.getCredentials());

        JwtAuthCredentials credentials = (JwtAuthCredentials) token.getCredentials();

        try {
            return buildUserDetails(credentials);
        } catch(AuthZException e) {
            throw new InsufficientAuthenticationException("Unable to authorize user", e);
        }
    }

    private AuthUserDetails buildUserDetails(JwtAuthCredentials credentials) {
        return AuthUserDetails.create()
                .authorities(authZService.fetchAuthorities(credentials.jwtToken()))
                .username(credentials.userId().toString())
                .userId(credentials.userId())
                .build();

    }

}
