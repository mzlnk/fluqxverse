package io.mzlnk.identitybroker.server.domain.identity.provider;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum IdentityProviderType {

    FACEBOOK("Facebook"),
    GITHUB("GitHub"),
    GOOGLE("Google"),
    KEYCLOAK("Keycloak"),
    MICROSOFT("Microsoft"),
    OKTA("Okta");

    private final String brandName;

}
