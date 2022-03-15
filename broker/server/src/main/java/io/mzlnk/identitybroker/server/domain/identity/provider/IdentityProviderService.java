package io.mzlnk.identitybroker.server.domain.identity.provider;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentityProviderService {

    private static final List<IdentityProvider> IDENTITY_PROVIDER_LIST = List.of(
            new IdentityProvider(IdentityProviderType.FACEBOOK, "some-client-id", "some-redirect-uri"),
            new IdentityProvider(IdentityProviderType.GOOGLE, "621232719841-knth7tdmld2h69qvqd6l6ht30biio2oj.apps.googleusercontent.com", "http://localhost:5000"),
            new IdentityProvider(IdentityProviderType.GITHUB, "some-client-id", "some-redirect-uri"),
            new IdentityProvider(IdentityProviderType.KEYCLOAK, "some-client-id", "some-redirect-uri"),
            new IdentityProvider(IdentityProviderType.MICROSOFT, "some-client-id", "some-redirect-uri"),
            new IdentityProvider(IdentityProviderType.OKTA, "some-client-id", "some-redirect-uri")
    );

    public List<IdentityProvider> listIdentityProviders() {
        return IDENTITY_PROVIDER_LIST;
    }

}
