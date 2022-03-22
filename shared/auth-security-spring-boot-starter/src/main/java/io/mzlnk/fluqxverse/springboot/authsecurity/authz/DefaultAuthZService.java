package io.mzlnk.fluqxverse.springboot.authsecurity.authz;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;

public class DefaultAuthZService implements AuthZService {

    @Override
    public List<? extends GrantedAuthority> fetchAuthorities(String token) throws AuthZException {
        return Collections.emptyList();
    }

}
