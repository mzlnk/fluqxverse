package io.mzlnk.identitybroker.server.application.config.callback;

import io.mzlnk.identitybroker.server.domain.callback.AuthCallbackTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class AuthCallbackConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "auth.callback")
    public AuthCallbackProperties authCallbackProperties() {
        return new AuthCallbackProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "auth")
    public AuthCallbackProviderProperties authProviderProperties() {
        return new AuthCallbackProviderProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "auth.jwt.token")
    public AuthCallbackTokenProperties jwtTokenProperties() {
        return new AuthCallbackTokenProperties();
    }

    @Bean
    public AuthCallbackTokenService jwtService(@Value("${auth.jwt.validation.private-key}") RSAPrivateKey privateKey,
                                               @Value("${auth.jwt.validation.public-key}") RSAPublicKey publicKey,
                                               AuthCallbackTokenProperties tokenProperties) {
        return new AuthCallbackTokenService(tokenProperties, privateKey, publicKey);
    }

}
