package io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto;

import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProviderType;

import java.util.List;

public record UserDetails(Long id,
                          String email,
                          List<IdentityProviderType> linkedProviders) {

}