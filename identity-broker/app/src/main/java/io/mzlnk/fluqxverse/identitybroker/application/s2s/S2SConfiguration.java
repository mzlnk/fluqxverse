package io.mzlnk.fluqxverse.identitybroker.application.s2s;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S2SConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "s2s")
    public S2SProperties s2sProperties() {
        return new S2SProperties();
    }

}
