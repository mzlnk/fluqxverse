package io.mzlnk.identitybroker.server.domain.identity;

import io.mzlnk.identitybroker.server.domain.identity.exchange.IdentityExchange;
import io.mzlnk.identitybroker.server.domain.identity.exchange.IdentityExchangeDetails;
import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;
import io.mzlnk.identitybroker.server.domain.user.User;
import io.mzlnk.identitybroker.server.domain.user.UserStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderNotSupportedException.identityProviderNotSupported;

@Service
public class IdentityManager {

    private final IdentityRepository identityRepository;
    private final UserStorage userStorage;
    private final Map<IdentityProviderType, IdentityExchange> identityExchanges;

    public IdentityManager(IdentityRepository identityRepository,
                           UserStorage userStorage,
                           List<IdentityExchange> identityExchanges) {

        this.identityRepository = identityRepository;
        this.userStorage = userStorage;

        this.identityExchanges = identityExchanges.stream()
                .collect(Collectors.toMap(IdentityExchange::getSupportedIdentityProvider, Function.identity()));
    }

    public Identity establishIdentity(OAuth2AuthorizationCode auth) {
        IdentityExchange identityExchange = Optional.ofNullable(this.identityExchanges.get(auth.provider()))
                .orElseThrow(identityProviderNotSupported(auth.provider()));

        IdentityExchangeDetails identityDetails = identityExchange.exchangeAuthorizationCodeForIdentity(auth.authorizationCode());

        User user = userStorage.findByEmail(identityDetails.email())
                .orElseGet(createUserFromIdentity(identityDetails));

        Identity identity = Identity.builder()
                .identityId(Identity.IdentityId.from(identityDetails.id(), auth.provider()))
                .user(user)
                .build();

        return identityRepository.save(identity);
    }

    private Supplier<User> createUserFromIdentity(IdentityExchangeDetails identityDetails) {
        return () -> userStorage.create(new UserStorage.CreateUserDetails(identityDetails.email()));
    }

}
