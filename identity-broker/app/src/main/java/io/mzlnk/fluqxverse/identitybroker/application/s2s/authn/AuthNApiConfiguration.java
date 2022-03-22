package io.mzlnk.fluqxverse.identitybroker.application.s2s.authn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class AuthNApiConfiguration {

    @Bean
    public AuthNApi defaultAuthNApi() {
        return new DefaultAuthNApi();
    }

    @Bean
    @Primary
    @Profile("dev")
    public AuthNApi devAuthNApi() {
        return new DevAuthNApi();
    }

}
