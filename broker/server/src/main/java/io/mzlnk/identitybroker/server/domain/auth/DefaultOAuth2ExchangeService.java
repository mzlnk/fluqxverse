package io.mzlnk.identitybroker.server.domain.auth;

import io.mzlnk.identitybroker.server.domain.identityprovider.IdentityProviderType;
import io.mzlnk.oauth2.exchange.core.authorizationcode.response.dto.OAuth2TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultOAuth2ExchangeService implements OAuth2ExchangeService {

    @Override
    public OAuth2TokenResponse exchangeCode(String code, IdentityProviderType provider) {
        // TODO: implement it
        return null;
    }
}

