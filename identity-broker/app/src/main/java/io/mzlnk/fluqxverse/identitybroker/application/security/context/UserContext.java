package io.mzlnk.fluqxverse.identitybroker.application.security.context;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import java.util.List;
import java.util.Optional;

public class UserContext {

    public static Long getUserId() {
        return userDetails().getUserId();
    }

    public static List<String> getScopes() {
        return userDetails().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    private static AuthUserDetails userDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .filter(principal -> principal instanceof AuthUserDetails)
                .map(AuthUserDetails.class::cast)
                .orElseThrow(() -> new PreAuthenticatedCredentialsNotFoundException("Unable to authenticate user"));
    }

}
