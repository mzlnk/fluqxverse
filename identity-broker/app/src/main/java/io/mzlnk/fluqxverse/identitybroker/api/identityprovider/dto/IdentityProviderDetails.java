package io.mzlnk.fluqxverse.identitybroker.api.identityprovider.dto;

import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProviderType;

public record IdentityProviderDetails(IdentityProviderType type,
                                      String clientId,
                                      String redirectUri) {

}
