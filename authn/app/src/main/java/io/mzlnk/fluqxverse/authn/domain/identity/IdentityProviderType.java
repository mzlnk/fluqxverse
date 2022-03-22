package io.mzlnk.fluqxverse.authn.domain.identity;

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
