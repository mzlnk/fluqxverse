package io.mzlnk.fluqxverse.authn.application.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mzlnk.fluqxverse.authn.application.security.authz.DefaultAuthZService;
import io.mzlnk.fluqxverse.springboot.authsecurity.authn.AuthNService;
import io.mzlnk.fluqxverse.springboot.authsecurity.authn.DefaultAuthNService;
import io.mzlnk.fluqxverse.springboot.authsecurity.authz.AuthZService;
import io.mzlnk.fluqxverse.springboot.authsecurity.credentials.*;
import io.mzlnk.fluqxverse.springboot.authsecurity.error.DefaultAccessDeniedHandler;
import io.mzlnk.fluqxverse.springboot.authsecurity.error.DefaultAuthenticationEntryPoint;
import io.mzlnk.fluqxverse.springboot.authsecurity.error.DefaultAuthenticationFailureHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class SecurityDefaultConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "security.jwt.token")
    public TokenProperties tokenProperties() {
        return new TokenProperties();
    }

    @Bean
    public TokenReader defaultTokenReader() {
        return DelegatingTokenReader.builder()
                .delegate(new HttpHeaderTokenReader())
                .delegate(new HttpCookieTokenReader())
                .build();
    }

    @Bean
    public AuthNService defaultAuthNService(TokenProperties tokenProperties) {
        return new DefaultAuthNService(tokenProperties);
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
