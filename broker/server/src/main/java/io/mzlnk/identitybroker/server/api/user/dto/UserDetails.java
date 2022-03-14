package io.mzlnk.identitybroker.server.api.user.dto;

import io.mzlnk.identitybroker.server.domain.identityprovider.IdentityProviderType;

import java.util.List;

public record UserDetails(Long id,
                          String email,
                          List<IdentityProviderType> linkedProviders) {

}
