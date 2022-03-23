package io.mzlnk.fluqxverse.authn.api.identity.dto;

import io.mzlnk.fluqxverse.authn.domain.identity.IdentityProviderType;

import javax.validation.constraints.NotNull;

public record IdentityCreateRequest(@NotNull String id,
                                    @NotNull IdentityProviderType provider,
                                    @NotNull Long userId) {
}
