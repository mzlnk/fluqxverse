package io.mzlnk.identitybroker.server.domain.identityprovider;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentityProviderService {

    private static final List<IdentityProvider> IDENTITY_PROVIDER_LIST = List.of(
            new IdentityProvider(IdentityProviderType.FACEBOOK, "some-client-id", "some-redirect-uri"),
            new IdentityProvider(IdentityProviderType.GOOGLE, "some-client-id", "some-redirect-uri"),
            new IdentityProvider(IdentityProviderType.GITHUB, "some-client-id", "some-redirect-uri"),
            new IdentityProvider(IdentityProviderType.KEYCLOAK, "some-client-id", "some-redirect-uri"),
            new IdentityProvider(IdentityProviderType.MICROSOFT, "some-client-id", "some-redirect-uri"),
            new IdentityProvider(IdentityProviderType.OKTA, "some-client-id", "some-redirect-uri")
    );

    public List<IdentityProvider> listIdentityProviders() {
        return IDENTITY_PROVIDER_LIST;
    }

}
