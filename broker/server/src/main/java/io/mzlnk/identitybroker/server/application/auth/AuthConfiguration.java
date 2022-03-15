package io.mzlnk.identitybroker.server.application.auth;

import io.mzlnk.identitybroker.server.application.auth.jwt.JwtService;
import io.mzlnk.identitybroker.server.application.auth.jwt.JwtTokenProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class AuthConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "auth.callback")
    public AuthCallbackProperties authCallbackProperties() {
        return new AuthCallbackProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "auth.jwt.token")
    public JwtTokenProperties jwtTokenProperties() {
        return new JwtTokenProperties();
    }

    @Bean
    public JwtService jwtService(@Value("${auth.jwt.validation.private-key}") RSAPrivateKey privateKey,
                                 @Value("${auth.jwt.validation.public-key}") RSAPublicKey publicKey,
                                 JwtTokenProperties jwtTokenProperties) {
        return new JwtService(jwtTokenProperties, privateKey, publicKey);
    }

}
