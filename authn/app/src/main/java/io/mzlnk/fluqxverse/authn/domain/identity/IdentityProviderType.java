package io.mzlnk.fluqxverse.authn.domain.identity;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.mzlnk.fluqxverse.authn.common.exception.InvalidEnumException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

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

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static IdentityProviderType from(String s) {
        try {
            return IdentityProviderType.valueOf(s);
        } catch(IllegalArgumentException e) {
            throw new InvalidEnumException(s, "should be one of %s".formatted(Arrays.toString(IdentityProviderType.values())));
        }
    }

}
