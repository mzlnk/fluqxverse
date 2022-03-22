package io.mzlnk.fluqxverse.authn.api.user.dto;

import io.mzlnk.fluqxverse.authn.domain.identity.Identity;
import io.mzlnk.fluqxverse.authn.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDetails toUserDetails(User user) {
        final var linkedProviders = user.getIdentities().stream()
                .map(Identity::getIdentityProviderType)
                .toList();

        return new UserDetails(
                user.getId(),
                user.getEmail(),
                linkedProviders
        );
    }

}
