package io.mzlnk.fluqxverse.springboot.authsecurity.authz;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AuthZService {

    List<? extends GrantedAuthority> fetchAuthorities(String token) throws AuthZException;

}
