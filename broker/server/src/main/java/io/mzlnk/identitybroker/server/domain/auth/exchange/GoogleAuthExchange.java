package io.mzlnk.identitybroker.server.domain.auth.exchange;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.mzlnk.identitybroker.server.application.auth.AuthProviderProperties;
import io.mzlnk.identitybroker.server.application.auth.jwt.JwtService;
import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static io.mzlnk.identitybroker.server.application.auth.AuthProviderProperties.AuthProviderDetails;

@Service
@ConditionalOnProperty(prefix = "auth.providers.GOOGLE", name = "enabled", havingValue = "true")
public class GoogleAuthExchange implements AuthExchange {

    private final JwtService jwtService;
    private final AuthProviderDetails googleAuthDetails;
    private final AuthorizationCodeFlow flow;

    public GoogleAuthExchange(AuthProviderProperties authProviderProperties,
                              JwtService jwtService) {
        var googleAuthDetails = authProviderProperties.getProvider(IdentityProviderType.GOOGLE);

        this.jwtService = jwtService;
        this.googleAuthDetails = googleAuthDetails;
        this.flow = initializeAuthorizationCodeFlow(googleAuthDetails);
    }

    @Override
    public IdentityProviderType getSupportedIdentityProvider() {
        return IdentityProviderType.GOOGLE;
    }

    @Override
    public AuthExchangeDetails exchangeAuthorizationCodeForIdentity(String code) {
        try {
            TokenResponse tokenResponse = flow.newTokenRequest(code)
                    .setRedirectUri(googleAuthDetails.getRedirectUri())
                    .execute();

            var decodedToken = jwtService.decodeToken((String) tokenResponse.get("id_token"));
            String id = decodedToken.getSubject();
            String email = decodedToken.getClaim("email").asString();

            return new AuthExchangeDetails(id, email);
        } catch (IOException e) {
            throw new RuntimeException(e); // TODO: handle it properly
        }
    }

    private AuthorizationCodeFlow initializeAuthorizationCodeFlow(AuthProviderDetails googleAuthDetails) {
        return new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                googleAuthDetails.getClientId(),
                googleAuthDetails.getClientSecret(),
                List.of("profile", "email")
        ).build();
    }


}
