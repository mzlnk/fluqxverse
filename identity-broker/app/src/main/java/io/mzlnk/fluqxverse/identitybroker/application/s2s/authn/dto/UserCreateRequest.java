package io.mzlnk.fluqxverse.identitybroker.application.s2s.authn.dto;

import lombok.Data;

@Data
public class UserCreateRequest {

    private final String email;
    private final String username;

}
