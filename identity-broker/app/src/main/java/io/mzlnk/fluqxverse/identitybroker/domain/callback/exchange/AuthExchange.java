package io.mzlnk.fluqxverse.identitybroker.domain.callback.exchange;

import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProviderType;

public interface AuthExchange {

    IdentityProviderType getSupportedIdentityProvider();
    AuthExchangeDetails exchangeAuthorizationCodeForIdentity(String code);

}
