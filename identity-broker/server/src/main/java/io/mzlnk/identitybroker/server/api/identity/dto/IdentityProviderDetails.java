package io.mzlnk.identitybroker.server.api.identity.dto;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;

public record IdentityProviderDetails(IdentityProviderType type,
                                      String clientId,
                                      String redirectUri) {

}
