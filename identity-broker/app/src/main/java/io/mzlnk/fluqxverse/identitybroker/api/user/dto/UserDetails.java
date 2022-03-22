package io.mzlnk.fluqxverse.identitybroker.api.user.dto;

import io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderType;

import java.util.List;

public record UserDetails(Long id,
                          String email,
                          List<IdentityProviderType> linkedProviders) {

}
