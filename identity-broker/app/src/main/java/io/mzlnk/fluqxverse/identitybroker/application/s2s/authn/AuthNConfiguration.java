package io.mzlnk.fluqxverse.identitybroker.application.s2s.authn;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.S2SProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class AuthNConfiguration {

    @Bean
    @Profile("!dev")
    public AuthNApi authNApi(S2SProperties s2sProperties) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(AuthNApi.class, s2sProperties.getAuthNUrl());

    }

    @Bean
    public AuthNService defaultAuthNService(AuthNApi authNApi) {
        return new DefaultAuthNService(authNApi);
    }

    @Bean
    @Primary
    @Profile("dev")
    public AuthNService devAuthNService() {
        return new DevAuthNService();
    }

}
