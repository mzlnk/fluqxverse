package io.mzlnk.identitybroker.server.application.security.auth;

import io.mzlnk.identitybroker.server.application.security.auth.authn.AuthNService;
import io.mzlnk.identitybroker.server.application.security.auth.credentials.JwtAuthCredentials;
import io.mzlnk.identitybroker.server.application.security.auth.credentials.TokenReader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthUserDetailsFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final TokenReader tokenReader;
    private final AuthNService authNService;

    public AuthUserDetailsFilter(AuthenticationManager authenticationManager,
                                 AuthenticationFailureHandler authenticationFailureHandler,
                                 TokenReader tokenReader,
                                 AuthNService authNService) {
        this.setAuthenticationManager(authenticationManager);
        this.setAuthenticationFailureHandler(authenticationFailureHandler);
        this.setContinueFilterChainOnUnsuccessfulAuthentication(false);

        this.tokenReader = tokenReader;
        this.authNService = authNService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            super.doFilter(request, response, chain);
        } catch (AuthenticationException e) {
            super.unsuccessfulAuthentication((HttpServletRequest) request, (HttpServletResponse) response, e);
        }
    }

    @Override
    protected Long getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return this.authNService.getPrincipal(tokenReader.readToken(request));
    }

    @Override
    protected JwtAuthCredentials getPreAuthenticatedCredentials(HttpServletRequest request) {
        return this.authNService.getCredentials(tokenReader.readToken(request));
    }

}
