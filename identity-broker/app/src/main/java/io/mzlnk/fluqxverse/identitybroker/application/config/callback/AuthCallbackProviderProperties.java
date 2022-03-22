package io.mzlnk.fluqxverse.identitybroker.application.config.callback;

import io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderType;
import lombok.Data;

import java.util.Map;
import java.util.Optional;

import static io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderNotSupportedException.identityProviderNotSupported;

@Data
public class AuthCallbackProviderProperties {

    private Map<IdentityProviderType, AuthProviderDetails> providers;

    public AuthProviderDetails getProvider(IdentityProviderType identityProviderType) {
        return Optional.ofNullable(this.providers.get(identityProviderType))
                .orElseThrow(identityProviderNotSupported(identityProviderType));
    }

    @Data
    public static class AuthProviderDetails {

        private String clientId;
        private String clientSecret;
        private String redirectUri;
        private boolean enabled;

    }

}
