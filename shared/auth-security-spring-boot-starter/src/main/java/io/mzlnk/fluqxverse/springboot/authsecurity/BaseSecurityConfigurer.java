package io.mzlnk.fluqxverse.springboot.authsecurity;

import io.mzlnk.fluqxverse.springboot.authsecurity.authn.AuthNService;
import io.mzlnk.fluqxverse.springboot.authsecurity.authz.AuthZService;
import io.mzlnk.fluqxverse.springboot.authsecurity.credentials.TokenReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class BaseSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    private final TokenReader tokenReader;

    private final AuthNService authNService;
    private final AuthZService authZService;

    public BaseSecurityConfigurer(AuthenticationFailureHandler authenticationFailureHandler,
                                  AuthenticationEntryPoint authenticationEntryPoint,
                                  AccessDeniedHandler accessDeniedHandler,
                                  TokenReader tokenReader,
                                  AuthNService authNService,
                                  AuthZService authZService) {
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.tokenReader = tokenReader;
        this.authNService = authNService;
        this.authZService = authZService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        var provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(authUserDetailsService());

        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(authUserDetailsFilter(), BasicAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }

    private AuthUserDetailsService authUserDetailsService() {
        return new AuthUserDetailsService(authZService);
    }

    private AuthUserDetailsFilter authUserDetailsFilter() throws Exception {
        return new AuthUserDetailsFilter(
                authenticationManagerBean(),
                authenticationFailureHandler,
                tokenReader,
                authNService
        );
    }

}
