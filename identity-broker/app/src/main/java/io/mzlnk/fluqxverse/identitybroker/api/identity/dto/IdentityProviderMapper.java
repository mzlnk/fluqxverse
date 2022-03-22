package io.mzlnk.fluqxverse.identitybroker.api.identity.dto;

import io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProvider;
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
