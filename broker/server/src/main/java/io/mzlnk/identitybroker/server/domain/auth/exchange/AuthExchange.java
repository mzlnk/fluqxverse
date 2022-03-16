package io.mzlnk.identitybroker.server.domain.auth.exchange;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;

public interface AuthExchange {

    IdentityProviderType getSupportedIdentityProvider();
    AuthExchangeDetails exchangeAuthorizationCodeForIdentity(String code);

}
