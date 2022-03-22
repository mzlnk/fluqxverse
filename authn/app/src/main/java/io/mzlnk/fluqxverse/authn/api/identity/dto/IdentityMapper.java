package io.mzlnk.fluqxverse.authn.api.identity.dto;

import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProvider;
import org.springframework.stereotype.Component;

@Component
public class IdentityMapper {

    public IdentityDetails toIdentityProviderDetails(IdentityProvider identityProvider) {
        return new IdentityDetails(
                identityProvider.type(),
                identityProvider.clientId(),
                identityProvider.redirectUri()
        );
    }

}
