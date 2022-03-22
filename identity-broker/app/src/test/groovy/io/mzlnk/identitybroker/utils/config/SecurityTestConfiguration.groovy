package io.mzlnk.identitybroker.utils.config

import io.mzlnk.identitybroker.server.application.security.auth.authn.AuthNService
import io.mzlnk.identitybroker.server.application.security.auth.authz.AuthZService
import io.mzlnk.identitybroker.server.application.security.auth.credentials.TokenReader
import io.mzlnk.identitybroker.utils.security.AuthorizationFixture
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean

@TestConfiguration
class SecurityTestConfiguration {

    @MockBean
    private TokenReader tokenReader

    @MockBean
    private AuthNService authNService

    @MockBean
    private AuthZService authZService

    @Bean
    AuthorizationFixture authorizationFixture() {
        return new AuthorizationFixture()
    }

}
