package io.mzlnk.fluqxverse.identitybroker.domain.identityprovider;

import io.mzlnk.fluqxverse.identitybroker.application.config.callback.AuthCallbackProviderProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static io.mzlnk.fluqxverse.identitybroker.application.config.callback.AuthCallbackProviderProperties.AuthProviderDetails;

@Service
public class IdentityProviderStorage {

    private final List<IdentityProvider> enabledIdentityProviders;

    public IdentityProviderStorage(AuthCallbackProviderProperties providerProperties) {
        this.enabledIdentityProviders = retrieveEnabledProviders(providerProperties.getProviders());
    }

    public List<IdentityProvider> listIdentityProviders() {
        return enabledIdentityProviders;
    }

    private List<IdentityProvider> retrieveEnabledProviders(Map<IdentityProviderType, AuthProviderDetails> providers) {
        return providers.entrySet().stream()
                .filter(entry -> entry.getValue().isEnabled())
                .map(entry -> {
                    IdentityProviderType type = entry.getKey();
                    String clientId = entry.getValue().getClientId();
                    String redirectUri = entry.getValue().getRedirectUri();

                    return new IdentityProvider(type, clientId, redirectUri);
                })
                .toList();
    }

}
