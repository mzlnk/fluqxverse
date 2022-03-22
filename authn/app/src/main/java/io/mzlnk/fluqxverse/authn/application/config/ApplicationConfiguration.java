package io.mzlnk.fluqxverse.authn.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.jackson.ProblemModule;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new ProblemModule());
    }

}
