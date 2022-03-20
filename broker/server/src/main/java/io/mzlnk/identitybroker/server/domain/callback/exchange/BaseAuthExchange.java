package io.mzlnk.identitybroker.server.domain.callback.exchange;

import io.mzlnk.identitybroker.server.application.config.callback.AuthCallbackProviderProperties;
import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;

import static io.mzlnk.identitybroker.server.application.config.callback.AuthCallbackProviderProperties.AuthProviderDetails;

public abstract class BaseAuthExchange implements AuthExchange {

    private final IdentityProviderType providerType;
    protected final AuthProviderDetails authProviderDetails;

    protected BaseAuthExchange(IdentityProviderType providerType,
                               AuthCallbackProviderProperties providerProperties) {
        this.providerType = providerType;
        this.authProviderDetails = providerProperties.getProvider(providerType);
    }

    @Override
    public IdentityProviderType getSupportedIdentityProvider() {
        return providerType;
    }

}
