package io.mzlnk.identitybroker.server.domain.identity.exchange;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;
import io.mzlnk.oauth2.exchange.core.authorizationcode.GitHubAuthorizationCodeExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnBean(value = GitHubAuthorizationCodeExchange.class)
public class GitHubIdentityExchange implements IdentityExchange {

    private final GitHubAuthorizationCodeExchange authorizationCodeExchange;

    @Override
    public IdentityProviderType getSupportedIdentityProvider() {
        return IdentityProviderType.GITHUB;
    }

    @Override
    public IdentityExchangeDetails exchangeAuthorizationCodeForIdentity(String code) {
        return null;
    }

}
