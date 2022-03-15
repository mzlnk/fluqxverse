package io.mzlnk.identitybroker.server.api.identityprovider.dto;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;

public record IdentityProviderDetails(IdentityProviderType type,
                                      String clientId,
                                      String redirectUri) {

}
