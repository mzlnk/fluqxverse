package io.mzlnk.fluqxverse.identitybroker.domain.identityprovider;

public record IdentityProvider(IdentityProviderType type,
                               String clientId,
                               String redirectUri) {

}
