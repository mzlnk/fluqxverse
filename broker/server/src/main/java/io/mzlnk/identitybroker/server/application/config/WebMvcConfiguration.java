package io.mzlnk.identitybroker.server.application.config;

import io.mzlnk.identitybroker.server.application.converter.AuthCallbackContextConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new AuthCallbackContextConverter());
    }

}
