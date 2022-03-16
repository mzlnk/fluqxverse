package io.mzlnk.identitybroker.server.domain.auth;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;

public record OAuth2AuthorizationCodeDetails(String authorizationCode,
                                             IdentityProviderType provider) {
}
