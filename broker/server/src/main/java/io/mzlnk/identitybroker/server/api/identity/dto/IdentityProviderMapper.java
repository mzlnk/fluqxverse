package io.mzlnk.identitybroker.server.api.identity.dto;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProvider;
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
