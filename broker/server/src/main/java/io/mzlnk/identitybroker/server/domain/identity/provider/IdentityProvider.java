package io.mzlnk.identitybroker.server.domain.identity.provider;

public record IdentityProvider(IdentityProviderType type,
                               String clientId,
                               String redirectUri) {

}
