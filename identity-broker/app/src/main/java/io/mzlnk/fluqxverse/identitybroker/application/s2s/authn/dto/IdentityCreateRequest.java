package io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto;

import io.mzlnk.fluqxverse.identitybroker.domain.identityprovider.IdentityProviderType;
import lombok.Data;

@Data
public class IdentityCreateRequest {

    private final String id;
    private final IdentityProviderType provider;
    private final Long userId;

}
