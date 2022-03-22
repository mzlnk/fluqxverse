package io.mzlnk.fluqxverse.authn.application.security.authz;

import io.mzlnk.fluqxverse.springboot.authsecurity.authz.AuthZException;
import io.mzlnk.fluqxverse.springboot.authsecurity.authz.AuthZService;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;

public class DevAuthZService implements AuthZService {

    @Override
    public List<? extends GrantedAuthority> fetchAuthorities(String token) throws AuthZException {
        return Collections.emptyList();
    }

}
