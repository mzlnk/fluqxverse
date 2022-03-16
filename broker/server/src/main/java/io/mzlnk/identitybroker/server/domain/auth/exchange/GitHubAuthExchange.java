package io.mzlnk.identitybroker.server.domain.auth.exchange;

import io.mzlnk.identitybroker.server.application.auth.AuthProviderProperties;
import io.mzlnk.oauth2.exchange.core.authorizationcode.GitHubAuthorizationCodeExchange;
import io.mzlnk.oauth2.exchange.core.authorizationcode.client.GitHubOAuth2Client;
import io.mzlnk.oauth2.exchange.core.authorizationcode.response.dto.GitHubOAuth2TokenResponse;
import io.mzlnk.oauth2.exchange.core.authorizationcode.response.dto.OAuth2TokenResponse;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        OAuth2TokenResponse tokenResponse = exchange.exchangeAuthorizationCode(code);
        String token = tokenResponse.getAccessToken();

        GitHubUserDetails userDetails = retrieveUserDetails(token);
        return new AuthExchangeDetails(userDetails.userId(), userDetails.primaryEmail());
    }

    private GitHubUserDetails retrieveUserDetails(String token) {
        try {
            GitHub gitHub = new GitHubBuilder().withOAuthToken(token).build();

            var user = gitHub.getMyself();

            String primaryEmail = user.getEmails2().stream()
                    .filter(GHEmail::isPrimary)
                    .findFirst()
                    .map(GHEmail::getEmail)
                    .orElseThrow(IllegalStateException::new);

            String userId = String.valueOf(user.getId());

            return new GitHubUserDetails(userId, primaryEmail);
        } catch(IOException e) {
            throw new IllegalStateException(e); // TODO: handle properly
        }
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
                                            String primaryEmail) { }

}
