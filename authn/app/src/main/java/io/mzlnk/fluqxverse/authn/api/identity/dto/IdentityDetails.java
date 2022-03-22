package io.mzlnk.fluqxverse.authn.api.identity.dto;

import io.mzlnk.fluqxverse.authn.domain.identity.IdentityProviderType;

public record IdentityDetails(IdentityProviderType type,
                              String clientId,
                              String redirectUri) {

}
