package io.mzlnk.identitybroker.server.domain.identity.exchange;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;
import io.mzlnk.oauth2.exchange.core.authorizationcode.FacebookAuthorizationCodeExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnBean(value = FacebookAuthorizationCodeExchange.class)
public class FacebookIdentityExchange implements IdentityExchange {

    private final FacebookAuthorizationCodeExchange authorizationCodeExchange;

    @Override
    public IdentityProviderType getSupportedIdentityProvider() {
        return IdentityProviderType.FACEBOOK;
    }

    @Override
    public IdentityExchangeDetails exchangeAuthorizationCodeForIdentity(String code) {
        return null;
    }

}
