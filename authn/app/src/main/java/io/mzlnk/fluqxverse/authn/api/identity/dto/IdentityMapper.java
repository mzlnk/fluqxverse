package io.mzlnk.fluqxverse.authn.api.identity.dto;

import io.mzlnk.fluqxverse.authn.domain.identity.Identity;
import io.mzlnk.fluqxverse.authn.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class IdentityMapper {

    public IdentityResponse toIdentityResponse(Identity identity) {
        return new IdentityResponse(
                identity.getId(),
                identity.getIdentityProviderType(),
                this.toUserDetails(identity.getUser())
        );
    }

    public UserDetails toUserDetails(User user) {
        return new UserDetails(
                user.getId(),
                user.getEmail(),
                user.getUsername()
        );
    }

}
