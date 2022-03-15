package io.mzlnk.identitybroker.server.domain.identity.exchange;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;
import io.mzlnk.oauth2.exchange.core.authorizationcode.KeycloakAuthorizationCodeExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnBean(value = KeycloakAuthorizationCodeExchange.class)
public class KeycloakIdentityExchange implements IdentityExchange {

    private final KeycloakAuthorizationCodeExchange authorizationCodeExchange;

    @Override
    public IdentityProviderType getSupportedIdentityProvider() {
        return IdentityProviderType.KEYCLOAK;
    }

    @Override
    public IdentityExchangeDetails exchangeAuthorizationCodeForIdentity(String code) {
        return null;
    }

}
