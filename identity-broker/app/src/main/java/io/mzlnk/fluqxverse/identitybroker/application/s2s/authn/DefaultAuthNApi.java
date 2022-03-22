package io.mzlnk.fluqxverse.identitybroker.application.s2s.authn;

import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.IdentityCreateRequest;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.IdentityDetails;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.UserCreateRequest;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.UserDetails;

import java.util.Optional;

public class DefaultAuthNApi implements AuthNApi {

    @Override
    public UserDetails createUser(UserCreateRequest userCreateRequest) {
        return null; //FIXME: implement it
    }

    @Override
    public Optional<UserDetails> findUserByEmail(String email) {
        return Optional.empty(); //FIXME: implement it
    }

    @Override
    public IdentityDetails createIdentity(IdentityCreateRequest identityCreateRequest) {
        return null; //FIXME: implement it
    }

}
