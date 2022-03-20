package io.mzlnk.identitybroker.server.application.config.callback;

import lombok.Data;

@Data
public class AuthCallbackTokenProperties {

    private String issuer;
    private Long expirationTime;

}
