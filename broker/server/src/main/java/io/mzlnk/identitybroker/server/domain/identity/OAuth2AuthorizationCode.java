package io.mzlnk.identitybroker.server.domain.identity;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;

public record OAuth2AuthorizationCode(String authorizationCode,
                                      IdentityProviderType provider) {
}
