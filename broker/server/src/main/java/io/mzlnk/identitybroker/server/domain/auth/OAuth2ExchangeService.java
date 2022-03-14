package io.mzlnk.identitybroker.server.domain.auth;

import io.mzlnk.identitybroker.server.domain.identityprovider.IdentityProviderType;
import io.mzlnk.oauth2.exchange.core.authorizationcode.response.dto.OAuth2TokenResponse;

public interface OAuth2ExchangeService {

    OAuth2TokenResponse exchangeCode(String code, IdentityProviderType provider);

}
