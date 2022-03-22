package io.mzlnk.fluqxverse.identitybroker.domain.callback;

import io.mzlnk.fluqxverse.identitybroker.domain.identity.IdentityStorage;
import io.mzlnk.fluqxverse.identitybroker.domain.callback.exchange.AuthExchange;
import io.mzlnk.fluqxverse.identitybroker.domain.callback.exchange.AuthExchangeDetails;
import io.mzlnk.fluqxverse.identitybroker.domain.identity.Identity;
import io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderType;
import io.mzlnk.fluqxverse.identitybroker.domain.user.User;
import io.mzlnk.fluqxverse.identitybroker.domain.user.UserStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderNotSupportedException.identityProviderNotSupported;

@Service
public class AuthCallbackService {

    private final IdentityStorage identityStorage;
    private final UserStorage userStorage;
    private final AuthCallbackTokenService tokenService;

    private final Map<IdentityProviderType, AuthExchange> identityExchanges;

    public AuthCallbackService(IdentityStorage identityStorage,
                               UserStorage userStorage,
                               AuthCallbackTokenService tokenService,
                               List<AuthExchange> authExchanges) {
        this.identityStorage = identityStorage;
        this.userStorage = userStorage;
        this.tokenService = tokenService;

        this.identityExchanges = authExchanges.stream()
                .collect(Collectors.toMap(AuthExchange::getSupportedIdentityProvider, Function.identity()));
    }

    public AuthDetails establishAuthenticity(OAuth2AuthorizationCodeDetails oAuth2Details) {
        AuthExchange authExchange = Optional.ofNullable(this.identityExchanges.get(oAuth2Details.provider()))
                .orElseThrow(identityProviderNotSupported(oAuth2Details.provider()));

        AuthExchangeDetails authDetails = authExchange.exchangeAuthorizationCodeForIdentity(oAuth2Details.authorizationCode());

        User user = userStorage.findByEmail(authDetails.email())
                .orElseGet(createUserFromIdentity(authDetails));

        IdentityStorage.IdentityCreateDetails identityDetails = new IdentityStorage.IdentityCreateDetails(authDetails.id(), oAuth2Details.provider(), user);
        Identity identity = identityStorage.createIdentity(identityDetails);

        String token = tokenService.createAndSignToken(identity);
        return new AuthDetails(token);
    }

    private Supplier<User> createUserFromIdentity(AuthExchangeDetails identityDetails) {
        return () -> userStorage.create(new UserStorage.CreateUserDetails(identityDetails.email()));
    }

}
