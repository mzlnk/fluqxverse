package io.mzlnk.fluqxverse.identitybroker.domain.callback;

import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProviderType;

public record OAuth2AuthorizationCodeDetails(String authorizationCode,
                                             IdentityProviderType provider) {
}
