package io.mzlnk.identitybroker.server.domain.identity.exchange;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;

public interface IdentityExchange {

    IdentityProviderType getSupportedIdentityProvider();
    IdentityExchangeDetails exchangeAuthorizationCodeForIdentity(String code);

}
