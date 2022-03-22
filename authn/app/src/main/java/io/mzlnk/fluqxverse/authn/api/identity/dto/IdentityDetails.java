package io.mzlnk.fluqxverse.authn.api.identity.dto;

import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProviderType;

public record IdentityDetails(IdentityProviderType type,
                              String clientId,
                              String redirectUri) {

}
