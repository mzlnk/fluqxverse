package io.mzlnk.identitybroker.server.domain.auth.exchange;

import io.mzlnk.identitybroker.server.application.auth.AuthProviderProperties;
import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;

import static io.mzlnk.identitybroker.server.application.auth.AuthProviderProperties.AuthProviderDetails;

public abstract class BaseAuthExchange implements AuthExchange {

    private final IdentityProviderType providerType;
    protected final AuthProviderDetails authProviderDetails;

    protected BaseAuthExchange(IdentityProviderType providerType,
                               AuthProviderProperties authProviderProperties) {
        this.providerType = providerType;
        this.authProviderDetails = authProviderProperties.getProvider(providerType);
    }

    @Override
    public IdentityProviderType getSupportedIdentityProvider() {
        return providerType;
    }

}
