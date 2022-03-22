package io.mzlnk.fluqxverse.springboot.authsecurity.context;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthUserDetails extends User {

    private static final String MASKED_PASSWORD = "*****";

    public static AuthUserDetailsBuilder create() {
        return new AuthUserDetailsBuilder();
    }

    private final Long userId;

    private AuthUserDetails(String username,
                            boolean enabled,
                            boolean accountNonExpired,
                            boolean credentialsNonExpired,
                            boolean accountNonLocked,
                            Collection<? extends GrantedAuthority> authorities,
                            Long userId) {
        super(username, MASKED_PASSWORD, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public static class AuthUserDetailsBuilder {

        private User.UserBuilder userBuilder = User.builder();
        private Long userId;

        public AuthUserDetailsBuilder username(String username) {
            userBuilder = userBuilder.username(username);
            return this;
        }

        public AuthUserDetailsBuilder roles(String... roles) {
            userBuilder = userBuilder.roles(roles);
            return this;
        }

        public AuthUserDetailsBuilder authorities(GrantedAuthority... authorities) {
            userBuilder = userBuilder.authorities(authorities);
            return this;
        }

        public AuthUserDetailsBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            userBuilder = userBuilder.authorities(authorities);
            return this;
        }

        public AuthUserDetailsBuilder authorities(String... authorities) {
            userBuilder = userBuilder.authorities(authorities);
            return this;
        }

        public AuthUserDetailsBuilder accountExpired(boolean accountExpired) {
            userBuilder = userBuilder.accountExpired(accountExpired);
            return this;
        }

        public AuthUserDetailsBuilder accountLocked(boolean accountLocked) {
            userBuilder = userBuilder.accountLocked(accountLocked);
            return this;
        }

        public AuthUserDetailsBuilder credentialsExpired(boolean credentialsExpired) {
            userBuilder = userBuilder.credentialsExpired(credentialsExpired);
            return this;
        }

        public AuthUserDetailsBuilder disabled(boolean disabled) {
            userBuilder = userBuilder.disabled(disabled);
            return this;
        }

        public AuthUserDetailsBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public AuthUserDetails build() {
            UserDetails user = userBuilder.build();
            return new AuthUserDetails(
                    user.getUsername(),
                    user.isEnabled(),
                    user.isAccountNonExpired(),
                    user.isCredentialsNonExpired(),
                    user.isAccountNonLocked(),
                    user.getAuthorities(),
                    this.userId
            );
        }

    }

}
