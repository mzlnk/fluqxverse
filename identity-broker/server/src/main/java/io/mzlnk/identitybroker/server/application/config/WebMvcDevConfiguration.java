package io.mzlnk.identitybroker.server.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Profile("dev")
@Configuration
public class WebMvcDevConfiguration extends WebMvcDefaultConfiguration {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

}
