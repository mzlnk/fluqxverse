package io.mzlnk.identitybroker.server.application.security.auth.authz;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AuthZService {

    List<? extends GrantedAuthority> fetchAuthorities(Long userId) throws AuthZException;

}
