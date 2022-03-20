package io.mzlnk.identitybroker.server.application.security.auth.authz;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class DevAuthZService implements AuthZService {

    @Override
    public List<? extends GrantedAuthority> fetchAuthorities(Long userId) throws AuthZException {
        return Collections.emptyList();
    }

}
