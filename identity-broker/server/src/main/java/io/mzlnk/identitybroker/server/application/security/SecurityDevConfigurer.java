package io.mzlnk.identitybroker.server.application.security;

import io.mzlnk.identitybroker.server.application.security.auth.authn.AuthNService;
import io.mzlnk.identitybroker.server.application.security.auth.authz.AuthZService;
import io.mzlnk.identitybroker.server.application.security.auth.credentials.TokenReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Primary
@Profile("dev")
@Configuration
@EnableWebSecurity
public class SecurityDevConfigurer extends SecurityDefaultConfigurer {

    public SecurityDevConfigurer(AuthenticationFailureHandler failureHandler,
                                 AuthenticationEntryPoint authenticationEntryPoint,
                                 AccessDeniedHandler accessDeniedHandler,
                                 TokenReader tokenReader,
                                 AuthNService authNService,
                                 AuthZService authZService) {
        super(failureHandler, authenticationEntryPoint, accessDeniedHandler, tokenReader, authNService, authZService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http.formLogin().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .cors().disable();
    }

}
