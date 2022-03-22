package io.mzlnk.fluqxverse.identitybroker.domain.identity;

import io.mzlnk.fluqxverse.identitybroker.domain.identity.provider.IdentityProviderType;
import io.mzlnk.fluqxverse.identitybroker.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdentityStorage {

    private final IdentityRepository identityRepository;

    public Identity createIdentity(IdentityCreateDetails createDetails) {
        Identity identity = Identity.builder()
                .identityId(Identity.IdentityId.from(createDetails.id(), createDetails.provider()))
                .user(createDetails.user())
                .build();

        return identityRepository.save(identity);
    }

    public static record IdentityCreateDetails(String id,
                                               IdentityProviderType provider,
                                               User user) {
    }

}
