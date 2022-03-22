package io.mzlnk.fluqxverse.identitybroker.domain.identity.provider;

public record IdentityProvider(IdentityProviderType type,
                               String clientId,
                               String redirectUri) {

}
