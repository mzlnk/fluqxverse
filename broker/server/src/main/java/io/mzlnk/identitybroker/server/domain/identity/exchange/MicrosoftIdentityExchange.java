package io.mzlnk.identitybroker.server.domain.identity.exchange;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;
import io.mzlnk.oauth2.exchange.core.authorizationcode.MicrosoftAuthorizationCodeExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnBean(value = MicrosoftAuthorizationCodeExchange.class)
public class MicrosoftIdentityExchange implements IdentityExchange {

    private final MicrosoftAuthorizationCodeExchange authorizationCodeExchange;

    @Override
    public IdentityProviderType getSupportedIdentityProvider() {
        return IdentityProviderType.MICROSOFT;
    }

    @Override
    public IdentityExchangeDetails exchangeAuthorizationCodeForIdentity(String code) {
        return null;
    }


}
