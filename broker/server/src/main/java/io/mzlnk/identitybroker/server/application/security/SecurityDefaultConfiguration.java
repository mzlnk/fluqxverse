package io.mzlnk.identitybroker.server.application.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mzlnk.identitybroker.server.application.config.callback.AuthCallbackTokenProperties;
import io.mzlnk.identitybroker.server.application.security.auth.error.DefaultAccessDeniedHandler;
import io.mzlnk.identitybroker.server.application.security.auth.error.DefaultAuthenticationEntryPoint;
import io.mzlnk.identitybroker.server.application.security.auth.error.DefaultAuthenticationFailureHandler;
import io.mzlnk.identitybroker.server.application.security.auth.authn.AuthNService;
import io.mzlnk.identitybroker.server.application.security.auth.authn.DefaultAuthNService;
import io.mzlnk.identitybroker.server.application.security.auth.authz.AuthZService;
import io.mzlnk.identitybroker.server.application.security.auth.authz.DefaultAuthZService;
import io.mzlnk.identitybroker.server.application.security.auth.credentials.DelegatingTokenReader;
import io.mzlnk.identitybroker.server.application.security.auth.credentials.HttpCookieTokenReader;
import io.mzlnk.identitybroker.server.application.security.auth.credentials.HttpHeaderTokenReader;
import io.mzlnk.identitybroker.server.application.security.auth.credentials.TokenReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.security.interfaces.RSAPublicKey;

@Configuration
public class SecurityDefaultConfiguration {

    @Bean
    public TokenReader defaultTokenReader() {
        return DelegatingTokenReader.builder()
                .delegate(new HttpHeaderTokenReader())
                .delegate(new HttpCookieTokenReader())
                .build();
    }

    @Bean
    public AuthNService defaultAuthNService(TokenReader tokenReader,
                                            AuthCallbackTokenProperties tokenProperties,
                                            @Value("${auth.jwt.validation.public-key}") RSAPublicKey publicKey) {
        return new DefaultAuthNService(tokenReader, tokenProperties, publicKey);
    }

    @Bean
    public AuthZService defaultAuthZService() {
        return new DefaultAuthZService();
    }

    @Bean
    public AccessDeniedHandler defaultAccessDeniedHandler(ObjectMapper objectMapper) {
        return new DefaultAccessDeniedHandler(objectMapper);
    }

    @Bean
    public AuthenticationEntryPoint defaultAuthenticationEntryPoint(ObjectMapper objectMapper) {
        return new DefaultAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    public AuthenticationFailureHandler defaultAuthenticationFailureHandler(ObjectMapper objectMapper) {
        return new DefaultAuthenticationFailureHandler(objectMapper);
    }

}
