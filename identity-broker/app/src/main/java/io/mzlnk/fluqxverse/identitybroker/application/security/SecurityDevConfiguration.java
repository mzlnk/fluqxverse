package io.mzlnk.fluqxverse.identitybroker.application.security;

import io.mzlnk.fluqxverse.identitybroker.application.security.auth.authz.AuthZService;
import io.mzlnk.fluqxverse.identitybroker.application.security.auth.authz.DevAuthZService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class SecurityDevConfiguration {

    @Bean
    @Primary
    public AuthZService devAuthZService() {
        return new DevAuthZService();
    }

}
