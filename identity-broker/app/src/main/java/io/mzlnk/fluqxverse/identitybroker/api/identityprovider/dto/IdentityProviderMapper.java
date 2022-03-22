package io.mzlnk.fluqxverse.identitybroker.api.identityprovider.dto;

import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProvider;
import org.springframework.stereotype.Component;

@Component
public class IdentityProviderMapper {

    public IdentityProviderDetails toIdentityProviderDetails(IdentityProvider identityProvider) {
        return new IdentityProviderDetails(
                identityProvider.type(),
                identityProvider.clientId(),
                identityProvider.redirectUri()
        );
    }

}
