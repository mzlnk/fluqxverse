package io.mzlnk.identitybroker.server.api.user.dto;

import io.mzlnk.identitybroker.server.domain.identity.Identity;
import io.mzlnk.identitybroker.server.domain.user.User;
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
