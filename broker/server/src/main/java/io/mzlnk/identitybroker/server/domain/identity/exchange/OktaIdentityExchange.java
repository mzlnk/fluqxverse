package io.mzlnk.identitybroker.server.domain.identity.exchange;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;
import io.mzlnk.oauth2.exchange.core.authorizationcode.OktaAuthorizationCodeExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnBean(value = OktaAuthorizationCodeExchange.class)
public class OktaIdentityExchange implements IdentityExchange {

    private final OktaAuthorizationCodeExchange authorizationCodeExchange;

    @Override
    public IdentityProviderType getSupportedIdentityProvider() {
        return IdentityProviderType.OKTA;
    }

    @Override
    public IdentityExchangeDetails exchangeAuthorizationCodeForIdentity(String code) {
        return null;
    }

}
