package io.mzlnk.fluqxverse.authn.application.security;

import io.mzlnk.fluqxverse.springboot.authsecurity.BaseSecurityConfigurer;
import io.mzlnk.fluqxverse.springboot.authsecurity.authn.AuthNService;
import io.mzlnk.fluqxverse.springboot.authsecurity.authz.AuthZService;
import io.mzlnk.fluqxverse.springboot.authsecurity.credentials.TokenReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Profile("!dev")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityDefaultConfigurer extends BaseSecurityConfigurer {

    public SecurityDefaultConfigurer(AuthenticationFailureHandler authenticationFailureHandler,
                                     AuthenticationEntryPoint authenticationEntryPoint,
                                     AccessDeniedHandler accessDeniedHandler,
                                     TokenReader tokenReader,
                                     AuthNService authNService,
                                     AuthZService authZService) {

        super(authenticationFailureHandler, authenticationEntryPoint, accessDeniedHandler, tokenReader, authNService, authZService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/idb/api/v1/auth/callback/**")
                .antMatchers("/idb/api/v1/identity-providers/**");
    }

    @Override
    protected void configureInternal(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated();
    }

}
