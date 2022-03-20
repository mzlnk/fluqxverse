package io.mzlnk.identitybroker.server.application.security.context;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUserDetails extends User {

    private static final String MASKED_PASSWORD = "*****";

    private final Long userId;

    @Builder(builderMethodName = "create")
    private AuthUserDetails(String username,
                           Collection<? extends GrantedAuthority> authorities,
                           Long userId) {
        super(username, MASKED_PASSWORD, authorities);
        this.userId = userId;
    }

}
