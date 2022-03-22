package io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto;

import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProviderType;

public record IdentityCreateRequest(String id,
                                    IdentityProviderType identityProviderType,
                                    Long userId) {
}
