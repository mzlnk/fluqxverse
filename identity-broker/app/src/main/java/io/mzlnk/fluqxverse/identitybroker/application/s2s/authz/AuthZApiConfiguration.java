package io.mzlnk.fluqxverse.identitybroker.application.s2s.authz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class AuthZApiConfiguration {

    @Bean
    public AuthZApi defaultAuthZApi() {
        return new DefaultAuthZApi();
    }

    @Bean
    @Primary
    @Profile("dev")
    public AuthZApi devAuthZApi() {
        return new DevAuthZApi();
    }

}
