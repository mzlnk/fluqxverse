package io.mzlnk.identitybroker.server.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStorage {

    private final UserRepository userRepository;
    private final IdentityProviderUserRepository identityProviderUserRepository;

}
