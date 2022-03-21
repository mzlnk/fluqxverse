package io.mzlnk.identitybroker.server.application.config.callback;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
