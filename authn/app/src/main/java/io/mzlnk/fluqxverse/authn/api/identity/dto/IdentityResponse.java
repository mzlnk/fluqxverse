package io.mzlnk.fluqxverse.authn.api.identity.dto;

import io.mzlnk.fluqxverse.authn.domain.identity.IdentityProviderType;

public record IdentityResponse(String id,
                               IdentityProviderType provider,
                               UserDetails user) {

}
