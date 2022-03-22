package io.mzlnk.fluqxverse.authn.application.security;

import io.mzlnk.fluqxverse.authn.application.security.authz.DevAuthZService;
import io.mzlnk.fluqxverse.springboot.authsecurity.authz.AuthZService;
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
