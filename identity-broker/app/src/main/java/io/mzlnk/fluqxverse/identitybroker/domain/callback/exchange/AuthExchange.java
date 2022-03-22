package io.mzlnk.fluqxverse.identitybroker.domain.callback.exchange;

import io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderType;

public interface AuthExchange {

    IdentityProviderType getSupportedIdentityProvider();
    AuthExchangeDetails exchangeAuthorizationCodeForIdentity(String code);

}
