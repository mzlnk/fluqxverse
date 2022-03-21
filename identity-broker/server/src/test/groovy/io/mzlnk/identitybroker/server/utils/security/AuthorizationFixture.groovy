package io.mzlnk.identitybroker.server.utils.security

import io.mzlnk.identitybroker.server.application.security.auth.authn.AuthNService
import io.mzlnk.identitybroker.server.application.security.auth.authz.AuthZService
import io.mzlnk.identitybroker.server.application.security.auth.credentials.JwtAuthCredentials
import io.mzlnk.identitybroker.server.application.security.auth.credentials.TokenReader
import org.springframework.security.core.authority.SimpleGrantedAuthority

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.when

class AuthorizationFixture {

    private static final String TEST_TOKEN = 'test-token'

    private final TokenReader tokenReader
    private final AuthNService authNService
    private final AuthZService authZService

    AuthorizationFixture(TokenReader tokenReader,
                         AuthNService authNService,
                         AuthZService authZService) {
        this.tokenReader = tokenReader
        this.authNService = authNService
        this.authZService = authZService
    }

    AuthorizationStub authorize() {
        when(tokenReader.readToken(any())).thenReturn(TEST_TOKEN)
        when(authNService.getPrincipal(TEST_TOKEN)).thenReturn(1L)
        when(authNService.getCredentials(TEST_TOKEN)).thenReturn(new JwtAuthCredentials(TEST_TOKEN, 1L))

        new AuthorizationStub(authZService)
    }

    static class AuthorizationStub {

        private final AuthZService authZService

        AuthorizationStub(AuthZService authZService) {
            this.authZService = authZService
        }

        AuthorizationStub withScopes(String... scopes) {
            def authorities = scopes.collect { new SimpleGrantedAuthority(it) }
            when(authZService.fetchAuthorities(TEST_TOKEN)).thenReturn(authorities)
            this
        }

    }

}
