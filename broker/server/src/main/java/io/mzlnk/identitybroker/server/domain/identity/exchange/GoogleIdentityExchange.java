package io.mzlnk.identitybroker.server.domain.identity.exchange;

import io.mzlnk.identitybroker.server.application.auth.jwt.JwtService;
import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;
import io.mzlnk.oauth2.exchange.core.authorizationcode.GoogleAuthorizationCodeExchange;
import io.mzlnk.oauth2.exchange.core.authorizationcode.response.dto.GoogleOAuth2TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnBean(value = GoogleAuthorizationCodeExchange.class)
public class GoogleIdentityExchange implements IdentityExchange {

    private final GoogleAuthorizationCodeExchange authorizationCodeExchange;
    private final JwtService jwtService;

    @Override
    public IdentityProviderType getSupportedIdentityProvider() {
        return IdentityProviderType.GOOGLE;
    }

    @Override
    public IdentityExchangeDetails exchangeAuthorizationCodeForIdentity(String code) {
        GoogleOAuth2TokenResponse tokenResponse = (GoogleOAuth2TokenResponse) authorizationCodeExchange.exchangeAuthorizationCode(code);

        var decodedToken = jwtService.decodeToken((String) tokenResponse.get("id_token"));
        String id = decodedToken.getSubject();
        String email = decodedToken.getClaim("email").asString();

        return new IdentityExchangeDetails(id, email);
    }

}
