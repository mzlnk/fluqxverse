package io.mzlnk.identitybroker.server.domain.identityprovider;

public record IdentityProvider(IdentityProviderType type,
                               String clientId,
                               String redirectUri) {

}
