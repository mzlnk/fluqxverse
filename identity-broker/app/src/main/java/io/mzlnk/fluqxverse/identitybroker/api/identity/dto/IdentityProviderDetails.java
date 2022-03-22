package io.mzlnk.fluqxverse.identitybroker.api.identity.dto;

import io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderType;

public record IdentityProviderDetails(IdentityProviderType type,
                                      String clientId,
                                      String redirectUri) {

}
