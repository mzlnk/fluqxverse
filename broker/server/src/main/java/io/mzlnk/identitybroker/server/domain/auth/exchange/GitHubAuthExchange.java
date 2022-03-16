package io.mzlnk.identitybroker.server.domain.auth.exchange;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;
import io.mzlnk.oauth2.exchange.core.authorizationcode.GitHubAuthorizationCodeExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnBean(value = GitHubAuthorizationCodeExchange.class)
public class GitHubAuthExchange implements AuthExchange {

    private final GitHubAuthorizationCodeExchange authorizationCodeExchange;

    @Override
    public IdentityProviderType getSupportedIdentityProvider() {
        return IdentityProviderType.GITHUB;
    }

    @Override
    public AuthExchangeDetails exchangeAuthorizationCodeForIdentity(String code) {
        return null;
    }

}
