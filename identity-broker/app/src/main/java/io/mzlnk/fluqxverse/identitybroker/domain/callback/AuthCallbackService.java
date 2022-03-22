package io.mzlnk.fluqxverse.identitybroker.domain.callback;

import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.AuthNApi;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.IdentityCreateRequest;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.IdentityDetails;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.UserCreateRequest;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.UserDetails;
import io.mzlnk.fluqxverse.identitybroker.domain.callback.exchange.AuthExchange;
import io.mzlnk.fluqxverse.identitybroker.domain.callback.exchange.AuthExchangeDetails;
import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProviderType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProviderNotSupportedException.identityProviderNotSupported;

@Service
public class AuthCallbackService {

    private final AuthNApi authNApi;
    private final AuthCallbackTokenService tokenService;

    private final Map<IdentityProviderType, AuthExchange> identityExchanges;

    public AuthCallbackService(AuthNApi authNApi,
                               AuthCallbackTokenService tokenService,
                               List<AuthExchange> authExchanges) {
        this.authNApi = authNApi;
        this.tokenService = tokenService;

        this.identityExchanges = authExchanges.stream()
                .collect(Collectors.toMap(AuthExchange::getSupportedIdentityProvider, Function.identity()));
    }

    public AuthDetails establishAuthenticity(OAuth2AuthorizationCodeDetails oAuth2Details) {
        AuthExchange authExchange = Optional.ofNullable(this.identityExchanges.get(oAuth2Details.provider()))
                .orElseThrow(identityProviderNotSupported(oAuth2Details.provider()));

        AuthExchangeDetails authDetails = authExchange.exchangeAuthorizationCodeForIdentity(oAuth2Details.authorizationCode());

        UserDetails user = authNApi.findUserByEmail(authDetails.email())
                .orElseGet(createUserFromIdentity(authDetails));

        if (!user.linkedProviders().contains(oAuth2Details.provider())) {
            IdentityCreateRequest identityCreateRequest = new IdentityCreateRequest(authDetails.id(), oAuth2Details.provider(), user.id());
            IdentityDetails identity = authNApi.createIdentity(identityCreateRequest);
        }

        String token = tokenService.createAndSignToken(user);
        return new AuthDetails(token);
    }

    private Supplier<UserDetails> createUserFromIdentity(AuthExchangeDetails identityDetails) {
        return () -> authNApi.createUser(new UserCreateRequest(identityDetails.email(), identityDetails.email()));
    }

}
