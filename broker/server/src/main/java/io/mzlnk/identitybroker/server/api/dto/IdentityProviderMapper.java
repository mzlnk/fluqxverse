package io.mzlnk.identitybroker.server.api.dto;

import io.mzlnk.identitybroker.server.domain.identityprovider.IdentityProvider;
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
