package io.mzlnk.identitybroker.server.application.config.callback;

import io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderType;
import lombok.Data;

import java.util.Map;
import java.util.Optional;

import static io.mzlnk.identitybroker.server.domain.identity.provider.IdentityProviderNotSupportedException.identityProviderNotSupported;

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
