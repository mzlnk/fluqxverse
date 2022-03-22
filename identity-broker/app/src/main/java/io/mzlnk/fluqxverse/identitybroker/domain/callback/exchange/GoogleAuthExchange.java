package io.mzlnk.fluqxverse.identitybroker.domain.callback.exchange;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.mzlnk.fluqxverse.identitybroker.application.config.callback.AuthCallbackProviderProperties;
import io.mzlnk.fluqxverse.identitybroker.domain.callback.AuthCallbackTokenService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderType.GOOGLE;

@Service
@ConditionalOnProperty(prefix = "auth.providers.GOOGLE", name = "enabled", havingValue = "true")
public class GoogleAuthExchange extends BaseAuthExchange {

    private final AuthCallbackTokenService tokenService;
    private final AuthorizationCodeFlow flow;

    public GoogleAuthExchange(AuthCallbackProviderProperties providerProperties,
                              AuthCallbackTokenService tokenService) {
        super(GOOGLE, providerProperties);

        this.tokenService = tokenService;
        this.flow = initializeAuthorizationCodeFlow();
    }

    @Override
    public AuthExchangeDetails exchangeAuthorizationCodeForIdentity(String code) {
        try {
            TokenResponse tokenResponse = flow.newTokenRequest(code)
                    .setRedirectUri(authProviderDetails.getRedirectUri())
                    .execute();

            var decodedToken = tokenService.decodeToken((String) tokenResponse.get("id_token"));
            String id = decodedToken.getSubject();
            String email = decodedToken.getClaim("email").asString();

            return new AuthExchangeDetails(id, email);
        } catch (IOException e) {
            throw new AuthCallbackException(e.getMessage());
        }
    }

    private AuthorizationCodeFlow initializeAuthorizationCodeFlow() {
        return new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                authProviderDetails.getClientId(),
                authProviderDetails.getClientSecret(),
                List.of("profile", "email")
        ).build();
    }


}
