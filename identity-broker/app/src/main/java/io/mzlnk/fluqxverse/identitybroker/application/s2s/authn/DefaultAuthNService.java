package io.mzlnk.fluqxverse.identitybroker.application.s2s.authn;

import feign.FeignException;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.IdentityCreateRequest;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.IdentityDetails;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.UserCreateRequest;
import io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto.UserDetails;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DefaultAuthNService implements AuthNService {

    private final AuthNApi authNApi;

    @Override
    public UserDetails createUser(UserCreateRequest userCreateRequest) {
        return authNApi.createUser(userCreateRequest);
    }

    @Override
    public Optional<UserDetails> findUserByEmail(String email) {
        try {
            return Optional.of(authNApi.getUserByEmail(email));
        } catch(FeignException ex) {
            if(ex.status() == 404) {
                return Optional.empty();
            } else {
                throw ex;
            }
        }
    }

    @Override
    public IdentityDetails createIdentity(IdentityCreateRequest identityCreateRequest) {
        return authNApi.createIdentity(identityCreateRequest);
    }

}
