package io.mzlnk.fluqxverse.identitybroker.application.s2s.authn;

import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.IdentityCreateRequest;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.IdentityDetails;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.UserCreateRequest;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.UserDetails;

import java.util.Optional;

public interface AuthNService {

    Optional<UserDetails> findUserByEmail(String email);
    UserDetails createUser(UserCreateRequest userCreateRequest);
    IdentityDetails createIdentity(IdentityCreateRequest identityCreateRequest);

}
