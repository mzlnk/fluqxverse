package io.mzlnk.fluqxverse.identitybroker.application.config.callback;

import lombok.Data;

import java.security.interfaces.RSAPrivateKey;

@Data
public class AuthCallbackTokenProperties {

    private String issuer;
    private Long expirationTime;
    private RSAPrivateKey privateKey;

}
