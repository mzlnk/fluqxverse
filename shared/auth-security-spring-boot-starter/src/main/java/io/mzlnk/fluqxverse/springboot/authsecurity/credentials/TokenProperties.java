package io.mzlnk.fluqxverse.springboot.authsecurity.credentials;

import java.security.interfaces.RSAPublicKey;

public class TokenProperties {

    private String issuer;
    private RSAPublicKey publicKey;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

}
