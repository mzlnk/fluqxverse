package io.mzlnk.identitybroker.server.application.auth.jwt;

import lombok.Data;

@Data
public class JwtTokenProperties {

    private String issuer;
    private Long expirationTime;

}
