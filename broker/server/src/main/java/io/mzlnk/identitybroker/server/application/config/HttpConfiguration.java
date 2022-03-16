package io.mzlnk.identitybroker.server.application.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConfiguration {

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }

}
