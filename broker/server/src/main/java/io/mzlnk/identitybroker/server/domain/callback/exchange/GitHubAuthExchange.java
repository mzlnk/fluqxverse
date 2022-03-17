package io.mzlnk.identitybroker.server.domain.callback.exchange;

import io.mzlnk.identitybroker.server.application.auth.AuthProviderProperties;
import io.mzlnk.oauth2.exchange.core.ExchangeException;
import io.mzlnk.oauth2.exchange.core.authorizationcode.GitHubAuthorizationCodeExchange;
import io.mzlnk.oauth2.exchange.core.authorizationcode.client.GitHubOAuth2Client;
import io.mzlnk.oauth2.exchange.core.authorizationcode.response.dto.OAuth2TokenResponse;
import org.kohsuke.github.GHEmail;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType.GITHUB;


@Service
@ConditionalOnProperty(prefix = "auth.providers.GITHUB", name = "enabled", havingValue = "true")
public class GitHubAuthExchange extends BaseAuthExchange {

    private final GitHubAuthorizationCodeExchange exchange;

    public GitHubAuthExchange(AuthProviderProperties authProviderProperties) {
        super(GITHUB, authProviderProperties);
        this.exchange = initializeExchange();
    }

    @Override
    public AuthExchangeDetails exchangeAuthorizationCodeForIdentity(String code) {
        try {
            OAuth2TokenResponse tokenResponse = exchange.exchangeAuthorizationCode(code);
            String token = tokenResponse.getAccessToken();

            GitHubUserDetails userDetails = retrieveUserDetails(token);
            return new AuthExchangeDetails(userDetails.userId(), userDetails.primaryEmail());
        } catch (IOException | ExchangeException e) {
            throw new AuthCallbackException(e.getMessage());
        }
    }

    private GitHubUserDetails retrieveUserDetails(String token) throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken(token).build();

        var user = gitHub.getMyself();

        String primaryEmail = user.getEmails2().stream()
                .filter(GHEmail::isPrimary)
                .findFirst()
                .map(GHEmail::getEmail)
                .orElseThrow(IllegalStateException::new);

        String userId = String.valueOf(user.getId());

        return new GitHubUserDetails(userId, primaryEmail);
    }

    private GitHubAuthorizationCodeExchange initializeExchange() {
        return GitHubAuthorizationCodeExchange.builder()
                .oAuth2Client(new GitHubOAuth2Client(
                        authProviderDetails.getClientId(),
                        authProviderDetails.getClientSecret(),
                        authProviderDetails.getRedirectUri())
                )
                .build();
    }

    private static record GitHubUserDetails(String userId,
                                            String primaryEmail) {
    }

}
